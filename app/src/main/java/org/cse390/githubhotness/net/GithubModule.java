package org.cse390.githubhotness.net;

import android.app.Application;

import org.cse390.githubhotness.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Module for providing Github dependencies. Modules are responsible for
 * providing dependencies--i.e. creating objects that other objects will use.
 * <p>
 * This is based in part on Mirowslaw Stanek's example code in a similar app:
 * http://frogermcs.github.io/
 */
@Module
public class GithubModule {
  private static final int TIMEOUT_MILLIS = 60 * 1000;
  private static final String URL_GITHUB = "https://api.github.com";

  @Provides
  @Singleton
  public OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(logging);
    }

    builder
        .connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

    return builder.build();
  }

  @Provides
  @Singleton
  public Retrofit provideGithubRetrofit(Application application,
      OkHttpClient okHttpClient) {
    Retrofit.Builder builder = new Retrofit.Builder();
    builder
        .client(okHttpClient)
        .baseUrl(URL_GITHUB)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create());
    return builder.build();
  }

  @Provides
  @Singleton
  public GithubService provideGithubService(Retrofit retrofit) {
    return retrofit.create(GithubService.class);
  }
}
