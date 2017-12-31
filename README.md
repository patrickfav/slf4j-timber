# slf4j-timber

The motivation of this project was to ease using existing libraries
which use SLF4J as their logging framework on the Google Android platform
in combination with [Jake Wharton's Timber logging utility.](https://github.com/JakeWharton/timber)

This project is based on the [_official_ slf4j-android implementation](https://mvnrepository.com/artifact/org.slf4j/slf4j-android) (+ bugfixes)
but directs the logging calls mainly to `Timber.log(...);`.

This project is basically a (i) repackaging of the SLF4J API part, together with (ii)

 a very lightweight binding implementation that simply forwards all SLF4J log requests to the logger provided on the Google Android platform. The API part is compiled from the same code basis of the standard distribution. This is the reason why we decided to keep the version numbering in sync with the standard SLF4J releases in order to reflect the code basis from which it was built.
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
`Timber.log(...);`. The following table shows the mapping from SLF4J log levels
to LogCat log levels.

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
parameter. Each logger name will be used as the log message tag on the Android platform.
However, the length of such tags is currently limited to 23 characters
(23 = 32 - 8 for namespace prefix - 1 for C terminator). If the fully qualified class
name (or the name given as parameter at creation time) exceeds this limit
then it will be truncated by the LoggerFactory and the new Logger will
have the truncated name. The following examples illustrate this:

| Original Name                                           | Truncated Name          |
| -------------                                           | -------------           |
| `org.example.project.MyClass`                           | `o*.e*.p*.MyClass`        |
| `org.example.project.subproject.MyClass`                | `o*.e*.p*.s*.MyClass`     |
| `org.example.MyQuiteLongNamedClassOfTooMuchCharacters`  | `o*.e*.MyQuiteLongNamed*` |
| `o.e.project.subproject.MyClass`                        | `o.e.p*.s*.MyClass`       |
| `MyQuiteLongNamedClassNotInAPackage`                    | `MyQuiteLongNamedClassN*` |

### Limitations
The Android-Timber binding implementation currently does not support Markers.
All logging methods which have a Marker parameter simply delegate to the
corresponding method without a Marker parameter, i.e., the Marker parameter
is silently ignored.

## Digital Signatures

### Signed Jar

The provided JARs in the Github release page are signed with my private key:

    CN=Patrick Favre-Bulle, OU=Private, O=PF Github Open Source, L=Vienna, ST=Vienna, C=AT
    Validity: Thu Sep 07 16:40:57 SGT 2017 to: Fri Feb 10 16:40:57 SGT 2034
    SHA1: 06:DE:F2:C5:F7:BC:0C:11:ED:35:E2:0F:B1:9F:78:99:0F:BE:43:C4
    SHA256: 2B:65:33:B0:1C:0D:2A:69:4E:2D:53:8F:29:D5:6C:D6:87:AF:06:42:1F:1A:EE:B3:3C:E0:6D:0B:65:A1:AA:88

Use the jarsigner tool (found in your `$JAVA_HOME/bin` folder) folder to verify.

### Signed Commits

All tags and commits by me are signed with git with my private key:

    GPG key ID: 4FDF85343912A3AB
    Fingerprint: 2FB392FB05158589B767960C4FDF85343912A3AB

## Build

### Jar Sign

If you want to jar sign you need to provide a file `keystore.jks` in the
root folder with the correct credentials set in environment variables (
`OPENSOURCE_PROJECTS_KS_PW` and `OPENSOURCE_PROJECTS_KEY_PW`); alias is
set as `pfopensource`.

If you want to skip jar signing just change the skip configuration in the
`pom.xml` jar sign plugin to true:

    <skip>true</skip>

### Build with Maven

Use maven (3.1+) to create a jar including all dependencies

    mvn clean install

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
