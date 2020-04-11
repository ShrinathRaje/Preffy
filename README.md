# Preffy

Preffy is a library that wraps over Android's native [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences) class and gives you a clean and simple API to store the key value pairs. Both keys and values are encrypted, thus adding a security layer to protect your data.
# Download
## Repository
Add this in your root `build.gradle` file (**not** your module `build.gradle` file):
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
## Dependency
Add this to your module's `build.gradle` file
```
dependencies {
	...
	implementation 'com.github.ShrinathRaje:Preffy:1.0.0'
}
```
# Usage
Get the instance of Preffy:
```Java
Preffy preffy = Preffy.getInstance(context);
```
**Store values Asynchronously**- applies the changes immediately to in-memory shared preferences and writes the changes to disk asynchronously.
```Java
preffy.putString("Name", "Shrinath");  
preffy.putInt("Age", 23);
```
**Store the values Synchronously**- applies the changes immediately to in-memory shared preferences and commits the changes to disk synchronously. Returns the boolean indicating if the save operation was successful or not.
```Java
if(preffy.putFloatSync("Weight", 81.7f)) {  
    //successfully saved  
} else {  
    //error occurred  
}
```
**Read the values**
```Java
String name = preffy.getString("name");
```
If the key is not found, then default values are returned. Default values for boolean, float, int, long, double, String and Set\<String\> are false, 0.0f, -1, -1L, 0.0, "" and null respectively. 
```Java
String name = preffy.getString("name", "default name");
```
The above overloaded method returns the user specified default value if the key does not exists.
# License
```
Copyright (C) 2020, Shrinath Bhosale

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```