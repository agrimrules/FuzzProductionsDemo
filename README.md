# FuzzProductionsDemo
Demo project made for [Fuzz Productions](www.fuzzproductions.com).<br>

#### Project requirements 
Upon launch, your app should query [this endpoint](http://quizzes.fuzzstaging.com/quizzes/mobile/1/data.json) to provide content for the app. The format of the JSON should be self-explanatory.

Develop an app with an interface that displays the following content based on the data:

  1. All - Display all content
  2. Text - Display only the text content
  3. Images - Display only the image content

Tapping any image item should open the image in a full screen viewer.

Tapping any text item should navigate to a web view that has [this site](https://fuzzproductions.com/) loaded.

#### Steps Taken
1. Made use of [Retrofit](http://square.github.io/retrofit/) as my HTTP client. To perform network operations in the background.<br>
2. Made use of [Picasso](http://square.github.io/picasso/) as my Image Loader. To download and resize large images.
3. Made use of the Java Regular expression. ` "(?:([^:/?#]+):)?(?://([^/?#]*))?([^?#]*\\.(?:jpg|gif|png|jpeg))(?:\\?([^#]*))?(?:#(.*))?".`
which ensures that the given url belongs to an image.
4. For easier UI created 3 fragments for the 3 main requirements and arranged them using the Navigation drawer layout.
