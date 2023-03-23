# MusicWiki
This app is completely made in kotlin. it follows MVVM Architecure , as this app uses concept of view models to separate the application's logic 
and the prsentation logic from the UI.used RETROFIT for api callback to fetch data and load the data using view model class.
   Recycler View with DiffUtil class is used in every List , so that new updates in any list will be automatically created without repetations.
   Fragment is used with view pager 2 , so that in same screen{Activity} three swappable views can be created.
   uses picasso for Image Loading and cache. 
   

Features--> MusicWiki application provides the most Famouse Genre for the music , for the user who are intrested to know more about the music , they can 
            click on One of the Genre at a time on the 1st screen{Home page} which contains initally Top 10 Genre and if you click on EXPAND button at the bottom
            you will see many more genre will appear . example--Rock , than you will be directed to 2nd window , where the information about the
            Genre will be explained and will also contain three List that is TOP ALBUMS  TOP ARTIST , TOP TRACKS for that genre. you can scroll down on each list
            for more data and swap right-left to change between album, artist , tracks. after clikcing on one of the album you are sent to the 3rd screen that
            Contains the information about the Album clicked and a image, also releated Genre . now go back to 2nd screen so that top artist can be seen .after 
            click on any one of the artist on 2nd screen you will enter to 3rd screen{ artist info} which contains the no. of followers , playcount , some info about
            that artist , top albums for that artist, top tracks for the artist. Furthur you can know about any of the album in artist section.

assumptions --> minimum SDK should be equal and greater than KITKAT VERSION. 
               The user if connected to internet should have data and intenet speed should be capable of image loading.
               No internect connection will be handled by CONNECTIVITY SERVICE.
               
decisions -->   was confused to choose the version of viewmodel lifecycle  implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'            
               bcoz The latest version  Giving Runtime error of having duplicate classes , than i show at internet but nothing worked , but at looking
               to my previous learning apps , i decresed the version of viewmodel dependency than it all worked better without any error.
               whether to reuse the recycler adapter or not.   which flag to use was really confusing for the activity which was my intent and it was also
               the activity which was recently visited , so i must have to clear the TASK STACK , so that app don't crashes out of memory . so i cleard
               all the previous activity as User has recahed to Home Screen.
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
             
