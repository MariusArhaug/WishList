# Config

Here lies the config for:

- **Checkstyle** 
- **SpotBugs**

## Checkstyle

Checkstyle is using the google-java format to make sure our code format is following real world's best practices. Using this in addition with **Intelij's** plugin _auto_save_ that automatically formats the code on save.

## Spotbugs 

Often when dealing with mutations and object oriented programming, side effects can occur, to avoid such bugs, we use SpotBugs. Spotbugs runs in the pipeline and causes a failure if it happens to spot a bug telling us the developer that our MR was unsuccessfull. This leads us to not merge potentially buggy code to master
