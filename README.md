# rxfx

[![Build Status](https://travis-ci.org/cementovoz/rxfx.svg?branch=master)](https://travis-ci.org/cementovoz/rxfx)

Simple reactive extensions for JavaFX : 

  1 FXScheduler - for run Action in JavaFX thread, as example : 
  ``` java
    eventBus.observable(Exception.class)
                .observeOn(FXScheduler.instance())
                .subscribe(e -> {
                  ExceptionDialog dialog = new ExceptionDialog(e);
                  dialog.setResizable(true);
                  dialog.show();
                });
  ```
  
  2 FXEvents - for create Observable over JavaFX events, as example : 
  ``` java
      Button ok = new Button("Ok");
      FXEvents.events(ok).subscribe(e -> {
          System.out.println("Ok cliked!");
      })
  ```
  
How to include for your project in gradle :

1 Add new repository url
``` groovy
repositories {
    jcenter()
    maven {
        url 'https://dl.bintray.com/pelenthium/maven/'
    }
}
```
2 Add to dependencies list
  ``` groovy
    dependencies {
      compile 'com.github.cementovoz:rxfx:0.1'
      // other your dependencies
    }
  ```
