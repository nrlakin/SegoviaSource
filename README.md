README--SegoviaSource

Source code should be fairly self-explanatory.  We kept the structure of the
original Fujitsu source as intact as possible.  New, Segovia-specific classes
are in the .segovia package.  Obviously, we had to make changes to code
elsewhere; you can probably find most of these by commenting out imports from
the .segovia package and looking for red squiggly lines in a java editor.

Most of the heavy lifting in terms of interaction with the palm vein reader is
done in the classes with 'Thread' in the name.  If you end up rolling your own
software from scratch, I'd probably cut and paste the relevant code from here,
as it seems to do a pretty good job ensuring it gets a quality image.

The code is compiled by running JavaBuild.sh from its directory.  Sorry it's
so verbose; Fujitsu gave us only a Windows .bat file (bizarre, as their Windows
source is written for C#/VB), and I didn't do a great job translating it into
Unix commands.

External dependencies (including the Fujitsu auth library, f3bc4jav.jar) are
stored in WorkingSource/lib; the other one is a basic csv libary that is
referenced by CSVManager.  I think I may have made a constructor for the
Recipient object that takes a CSVRecord as an argument, too.

Feel free to reach out to Lucas or me with any questions.
