# REQRES POC Android

Reqres is a POC app that uses [REQRES](https://reqres.in/) to create an experience with a data driven REST-API.

![Clip](/assets/clip.gif?raw=true "REQRES POC")

[![made-with-kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
![OS](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

## Table of contents
* [Architecture](#architecture)
* [Installation](#installation)
* [Configuration](#configuration)
* [Usage](#usage)
* [Links](#links)
* [Technologies](#technologies)
* [Maintainers](#maintainers)
* [Contributing](#contributing)
* [Credits](#credits)

## Architecture

![logo](/assets/architecture.png "Architecture diagram")

More information? Take a look at the
* [Guide to app architecture](https://developer.android.com/jetpack/guide)

## Installation
As usual, clone this repository and import into **Android Studio**

```bash
git clone git@github.com:jgodinez/reqres-poc.git
```

## Configuration

##### Keystore
Include `buildSystem/keystore.jks` and `buildSystem/keystore.properties` with the following info:

```properties
storePassword='...'
storeFile='...'
releaseAlias='...'
releasePassword='...'
```

##### Build variants
Use the Android Studio *Build Variants* section to choose between **release** or **debug** build types.

![logo](/assets/build_configuration.png "Build configuration")

##### Build APK(s)

Override *outputFileName* on build app

```groovy
applicationVariants.all { variant ->
    variant.outputs.all {
        def appName = rootProject.name.toLowerCase()
        outputFileName = outputFileName.replace("app-", "$appName-").replaceAll(".apk", "-v${variant.versionName.replaceAll("-debug|-release", "")}.apk")
    }
}
```

This provides an output filename with the following structure:

*app name* - *variant* - *version*

```groovy
reqrespoc-debug-v0.1.0.1.apk
reqrespoc-release-v0.1.0.apk
```

Debug output file name includes *versionBuild* as the last value to identify the feature being tested.

##### versionBuild

*versionBuild* is included in app gradle configuration. 

This value is required for debug buildType as part of versionNameSuffix and it will be displayed on the app info section. It is also required to generate the application version code. 

The value must be modified according to the following rules:

- [x] Restored to default value (1) in every release candidate version
- [x] Increased by 1 according to each feature release for testing

## Usage
TODO

## Links

##### Source-control branching model

* [Trunk Based Development](https://trunkbaseddevelopment.com/)


##### Commit guidelines

* [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
* [keep a changelog](https://keepachangelog.com/en/1.0.0/)
* [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html)

##### Style guide

* [Kotlin style guide](https://developer.android.com/kotlin/style-guide)

## Technologies
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Retrofit](https://square.github.io/retrofit/)
* [Mockito](https://github.com/mockito/mockito)

## Maintainers
This project is mantained by:
* [Juan Godinez](https://github.com/jgodinez)

## Contributing
Want to contribute? Great! Just make pull request!

## Credits
* [REQRES](https://reqres.in/) - A hosted REST-API ready to respond to your AJAX requests.
