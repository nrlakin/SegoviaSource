export CLASSPATH=".:./lib/f3bc4jav.jar:./lib/commons-csv-1.1.jar"
echo $CLASSPATH > cp.txt

javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/exception/PsAplException.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/Recipient.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/CSVManager.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/SegoviaCSVFilter.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/SegoviaVeinDataFilenameFilter.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/RecipientDisplay.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/data/PsDataManager.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/data/PsLogManager.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/data/PsThreadResult.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/data/PsVeinDataFilenameFilter.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/event/PsBusinessListener.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/xml/PsFileAccessor.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/xml/PsFileAccessorIni.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/xml/PsFileAccessorLang.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsInputIdText.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsMainFrame.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsMessageDialog.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsSampleApl.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsSilhouetteLabel.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/segovia/SegoviaLogoLabel.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsStateCallback.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsStreamingCallback.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsThreadBase.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsThreadCancel.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsThreadEnroll.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsThreadIdentify.java
javac -cp $CLASSPATH ./com/fujitsu/frontech/palmsecure_smpl/PsThreadVerify.java

mkdir -p temp/com/fujitsu/frontech/palmsecure_smpl/exception
mkdir -p temp/com/fujitsu/frontech/palmsecure_smpl/data
mkdir -p temp/com/fujitsu/frontech/palmsecure_smpl/event
mkdir -p temp/com/fujitsu/frontech/palmsecure_smpl/xml
mkdir -p temp/com/fujitsu/frontech/palmsecure_smpl/segovia

cp com/fujitsu/frontech/palmsecure_smpl/*.class temp/com/fujitsu/frontech/palmsecure_smpl
cp com/fujitsu/frontech/palmsecure_smpl/data/*.class temp/com/fujitsu/frontech/palmsecure_smpl/data
cp com/fujitsu/frontech/palmsecure_smpl/event/*.class temp/com/fujitsu/frontech/palmsecure_smpl/event
cp com/fujitsu/frontech/palmsecure_smpl/xml/*.class temp/com/fujitsu/frontech/palmsecure_smpl/xml
cp com/fujitsu/frontech/palmsecure_smpl/exception/*.class temp/com/fujitsu/frontech/palmsecure_smpl/exception
cp com/fujitsu/frontech/palmsecure_smpl/segovia/*.class temp/com/fujitsu/frontech/palmsecure_smpl/segovia

pwd
cd ./com
find . -name "*.class" -type f -delete
cd ..

cd temp
echo "here" > here.txt
jar -cf PalmSecureSample_Java.jar com
cd ..

mv temp/PalmSecureSample_Java.jar PalmSecureSample_Java.jar
rm -rf temp

