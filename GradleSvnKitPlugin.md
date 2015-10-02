The Gradle SvnKit plugin is a Gradle plugin to attach a Gradle project to a Subversion repository

# Introduction #



# Usage #
Once the svnkit plugin is applied to a project with

```
            buildscript {
               repositories {
                  flatDir {
                     dirs new File( gradle.gradleHomeDir, 'lib/plugins')
                  }
               }
               dependencies {
                  classpath group: 'org.gradle.plugins', name: 'gradle-svnkit', version: '0.5-20101018'
                  classpath group: 'org.gradle.plugins', name: 'svnkit', version: ''
                  classpath group: 'org.gradle.plugins', name: 'svnkit-javahl', version: ''
               }
            }
            apply plugin: svnkit
```

the project acquires a set of tasks which allows to interact with a Subversion repository identified by the following variables:

```
   svn_username
   svn_password
   svn_root
   svn_project
   svn_trunk
```


The following is an example of how to request the password. Please note that:
  * variables svn\_username  and svn\_password are global
  * the same password is used in all subprojects
  * it is not a secure way to manage passwords
  * it was working only in a text console, i.e. from the command line.

```
subprojects {
  apply plugin: 'svnkit'

 // .......

 // subversion
 svnkit = project.convention.plugins.svnkit

 svnkit.svn_root = svn_root
 svnkit.svn_username = svn_username
 svnkit.svn_password = svn_password

 gradle.taskGraph.beforeTask { task ->
   if( task.name.startsWith("svnkit_") == true ) {
     if( task.project.svnkit.svn_password == null ||
task.project.svnkit.svn_password.equals("") ) {
       if( svn_password == null || svn_password.equals("") ) {
         Console cons;
         char[] passwd;
         if ((cons = System.console()) != null &&
             (passwd = cons.readPassword("[%s]", "Password:")) != null) {
             task.project.svnkit.svn_password = new String( passwd )
             svn_password = task.project.svnkit.svn_password
         }

       } else {
           task.project.svnkit.svn_password = svn_password

       }
     }
   }
 }
}
```

In case you need to do the same for many projects I suggest to use also my other plugin gradle-ws which allows to store values of common variables in a CSV file.