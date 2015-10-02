The gradle-ws plugin is a Gradle plugin to manage a set of projects, their conditional dependencies and the values of some of their common variables.

# Introduction #

This plugin has been created to simplify the management of a multi-project build with Gradle.

The main goal of the plugin is to manage a set of Gradle projects as a
workspace with values of variables stored in a CSV file and Gradle scripts
merged and synchronized in the root project.

Most important requirements to create the plugin have been:
  1. Not all projects needs to be present
  1. Each project must have a separate build.gradle script
  1. Each project may have a different values for same variables

The first requirement is needed when a release manager has to work with the code of a subsets of projects which may depends on other projects (either the code or the corresponding artifacts). So, the conditional dependency is needed in order to specify the dependency of a project on another one or its artifact.


We the projects to manage increase a lot it became not not very
easy to edit their build.gradle scripts. So, a kind
of synchronization mechanisms that aggregate the various scripts into
one has been developed. This way it is possible to edit one file and the plugin copy the changes into the corresponding build.gradle script.


By default the plugin uses two files:
- workspace.gradle
- workspace.csv

The first is used to collect the build.gradle script of the various
projects, while the second is used to collect the values of common
variables used by the projects. Few constraints to remember if you
want to test the script:
  * you can edit the workspace.gradle file but you should not change the
lines delimiting each project
```
project(':projA'){
  ...
}
project(':projB'){
 ...
}
```

  * the file workspace.csv must have at least two lines: one to specify
the name of the variables and one to specify the defualt values

```
 "project","svn_project","svn_trunk","svn_branches","svn_tags","svn_ws"
 "default","","trunk","branches","tags",""
 "prjA","svnPrjA","releases/prjA/trunk","releases/prjA/branches",,
 "prjB","svnPrjB","releases/prjB/trunk","releases/prjB/branches",,
 "prjC","svnPrjC","releases/prjC/trunk","releases/prjC/branches",,
```

  * conditional project dependency must be specified with ws\_project()


The plugin is in beta state and it need some face lifting. For example, it would be
nice to adopt SuperCsv library (http://supercsv.sourceforge.net/).
Also, the synchronization mechanism should be improved.