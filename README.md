# slf4j-timber

[Hashed Message Authentication Code](https://en.wikipedia.org/wiki/Hash-based_message_authentication_code) (HMAC)-based key derivation function ([HKDF](https://en.wikipedia.org/wiki/HKDF)), can be used as a building block in various protocols and applications.  The [key derivation function](https://en.wikipedia.org/wiki/Key_derivation_function) (KDF) is intended to support a wide range of applications and requirements, and is conservative in its use of [cryptographic hash functions](https://en.wikipedia.org/wiki/Cryptographic_hash_function). It is likely to have [better security properties](https://crypto.stackexchange.com/questions/13232/how-is-hkdf-expand-better-than-a-simple-hash) than KDF's based on just a hash functions alone. See [RFC 5869](https://tools.ietf.org/html/rfc5869) for full detail.

[![Download](https://api.bintray.com/packages/patrickfav/maven/slf4j-timber/images/download.svg)](https://bintray.com/patrickfav/maven/slf4j-timber/_latestVersion)
[![Build Status](https://travis-ci.org/patrickfav/slf4j-timber.svg?branch=master)](https://travis-ci.org/patrickfav/slf4j-timber)
[![Javadocs](https://www.javadoc.io/badge/at.favre.lib/slf4j-timber.svg)](https://www.javadoc.io/doc/at.favre.lib/slf4j-timber)
[![Coverage Status](https://coveralls.io/repos/github/patrickfav/slf4j-timber/badge.svg?branch=master)](https://coveralls.io/github/patrickfav/slf4j-timber?branch=master)


## Quickstart

Add dependency to your `pom.xml`:

    <dependency>
        <groupId>at.favre.lib</groupId>
        <artifactId>hkdf</artifactId>
        <version>{latest-version}</version>
    </dependency>

A very simple example:

```java
byte[] pseudoRandomKey = HKDF.fromHmacSha256().extract(null, lowEntropyInput);
byte[] outputKeyingMaterial = HKDF.fromHmacSha256().expand(pseudoRandomKey, null, 64);
```

## Download

The artifacts are deployed to [jcenter](https://bintray.com/bintray/jcenter) and [Maven Central](https://search.maven.org/).

### Maven

Add dependency to your `pom.xml`:

    <dependency>
        <groupId>at.favre.lib</groupId>
        <artifactId>slf4j-timber</artifactId>
        <version>{latest-version}</version>
    </dependency>

### Gradle

Add to your `build.gradle` module dependencies:

    compile group: 'at.favre.lib', name: 'slf4j-timber', version: '{latest-version}'

### Local Jar

[Grab jar from latest release.](https://github.com/patrickfav/slf4j-timber/releases/latest)


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
