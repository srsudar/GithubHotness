This is a demo app I am creating to experiment with Dagger2.

There is an Android bug in the support library regarding Coordinator Layout. A
writeup of that bug is
[here](https://code.google.com/p/android/issues/detail?id=222597). In working
on this demo I've stumbled into a reproducible test for this when using
Robolectric. I'm not sure if this is not due to poor configuration or an
incomplete test on my part, but given that as of 2016-11-02 it is still an
active bug, I thought I'd mark this for interested parties.

The failing test is `notNull` in `repoListActivityTest`. To run at the command
line:

```sh
./gradlew :app:testDebugUnitTest --stacktrace
```
