package org.cse390.githubhotness;

import org.cse390.githubhotness.net.GithubModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        AppModule.class,
        GithubModule.class
    }
)
public interface AppComponent {
}
