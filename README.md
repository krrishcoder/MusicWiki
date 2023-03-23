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
