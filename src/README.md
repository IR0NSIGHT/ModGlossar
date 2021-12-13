The glossar library allows mod creators to add an ingame wiki easily to their mod.
The glossar utility runs 100% clientside.
Create a category (ideally one titled like your mod) and add pages to it. add the catergory to the glossar:
```java
        GlossarInit.initGlossar(this);

        GlossarCategory cat = new GlossarCategory("Example Mod");

        cat.addEntry(new GlossarEntry("Introduction","the text goes here"));

        GlossarInit.addCategory(cat);
```
Look through the GlossarInit class,its the only one the modcreator is supposed to interact with.
Only one glossar is created, all mods using the libarry will add their catergories here.
a timed advertisment telling the player how to open the glossar is broadcast every 30 minutes, starting 10 seconds after join.
Currently the glossar can only show text, no pictures.

To be able to use the mod, you have to add it as a dependency to your intellij project:
project strucutre >> modules >> dependencies 
in the artifacts tab, you have to add "extracted jar" for the modglossar to your output, otherwise it will compile but crash bc it doesnt find the classes.
