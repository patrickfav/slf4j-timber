# slf4j-timber

The motivation of this project was to ease using existing libraries
which use SLF4J as their logging framework on the Google Android platform
in combination with [Jake Wharton's Timber logging utility.](https://github.com/JakeWharton/timber)

This project is based on the [_official_ slf4j-android implementation](https://mvnrepository.com/artifact/org.slf4j/slf4j-android) (+ bugfixes)
but directs the logging calls mainly to `Timber.log(...);`.

[![Download](https://api.bintray.com/packages/patrickfav/maven/slf4j-timber/images/download.svg)](https://bintray.com/patrickfav/maven/slf4j-timber/_latestVersion)
[![Build Status](https://travis-ci.org/patrickfav/slf4j-timber.svg?branch=master)](https://travis-ci.org/patrickfav/slf4j-timber)
[![Javadocs](https://www.javadoc.io/badge/at.favre.lib/slf4j-timber.svg)](https://www.javadoc.io/doc/at.favre.lib/slf4j-timber)
[![Coverage Status](https://coveralls.io/repos/github/patrickfav/slf4j-timber/badge.svg?branch=master)](https://coveralls.io/github/patrickfav/slf4j-timber?branch=master)

## Quickstart

Add the following to your dependencies ([add jcenter to your repositories](https://developer.android.com/studio/build/index.html#top-level) if you haven't)

```gradle
compile 'at.favre.lib:slf4j-timber:1.0.0'
```

And that's basically it. SLF4J will automatically look for implementations of `ILoggerFactory` in the classpath (so don't add this
parallel to `org.slf4j:slf4j-android`)

## Download

The artifacts are deployed to [jcenter](https://bintray.com/bintray/jcenter) and [Maven Central](https://search.maven.org/).

## Description

### Log level mapping
The priorities will be converted to LogCat's priority level and passed to
`Timber.log(...);`. The `Log.isLoggable()` are not respected here, since `Timber`
should be responsible to decide when to log what. The following table shows
the mapping from SLF4J log levels to LogCat log levels.

| SLF4J         | Android/Timber |
| ------------- |:-------------: |
| TRACE         | VERBOSE        |
| DEBUG         | DEBUG          |
| INFO          | INFO           |
| WARN          | WARN           |
| ERROR         | ERROR          |

### Logger name mapping

Logger instances created using the LoggerFactory are named either according to
the name given as parameter, or the fully qualified class name of the class given as
parameter. No truncating will take place since Timber handles this itself.

### Limitations

The Android-Timber binding implementation currently does not support Markers.
All logging methods which have a Marker parameter simply delegate to the
corresponding method without a Marker parameter, i.e., the Marker parameter
is silently ignored.

## Digital Signatures

### Signed Commits

All tags and commits by me are signed with git with my private key:

    GPG key ID: 4FDF85343912A3AB
    Fingerprint: 2FB392FB05158589B767960C4FDF85343912A3AB

## Build

Assemble the lib with the following command

    ./gradlew :assemble
    ./gradlew :hood-extended:assemble

The `.aar` files can then be found in `/hood-*/build/outputs/aar` folder


## Tech Stack

* Java 7
* Maven

# License

Copyright 2017 Patrick Favre-Bulle

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
