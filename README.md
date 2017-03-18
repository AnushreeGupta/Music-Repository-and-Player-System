# Music-Repository-and-Player-System

Search engine and database for songs with a media player to play the music along with some add-on functionalities to enhance the usability of the system.

There will be two governing actors in the system - End-User and the Administrator. Both will have certain rights specific to them for which they will have to login to the system.

Songs are the system’s main entity that will hold attributes like song_name through which user can will be able to perform search, song_id as primary key and movie_id for mapping songs with their respective Movies . Once search is completed Player can be used to play the songs. Instead of performing search user can make use of the default playlists - SingerPlaylist, AlbumPlayist and MoviePlaylist which are maintained by Administrator. User can create his own User Specific Playlists which will be visible only to him. All these playlists will inherit their functionalities from Playlist. As an add-on feature user can also view the lyrics for the currently playing song.

The system will allow users to perform search and listen to the songs via media player provided in the system. Song search can be done based on two parameters i.e song name and movie name. There will be an add on feature for user to get lyrics of the song he is listening to. Lyrics will be displayed if the user chooses the lyrics options. In addition to this system will provide some default playlist under albums, movies and singers which will be maintained by the Administrator . User can play these playlists directly without searching. User can also create his own playlist for future use and perform few operations like adding/ deleting songs on it. To use this feature, the new user should sign up to the system . Once registered, he can login to the system to create his own playlist and also modify existing playlist if any .

All of this needs to be implemented in such a way that it can be used on laptop/computer . There needs to be a provision for the system to be occasionally connected to a network in cases when the user needs to view the lyrics of the current song.

The user base for our system can range from a naive person who is first time user of such a system to a person who uses computer in his daily life . Thus in either case an intuitive user interface is absolutely necessary so that the end user is himself able to navigate through the system and figures out the next step to be taken to proceed.

Color Coding: Actors Noun Verb Phrases Operating System Ease of Use

Use-Cases with their respective actors:

Actors Use-Cases

1. End User
1. To Login in the system (for existing users)
2. To SignUp in the system (for new users)
3. To search a song by song_name or movie_name
4. To view lyrics of the current song.
5. To create a user playlist
6. To modify a user playlist
7. To change password
8. To play songs
9. To logout from the system

2. Administrator

1. To add new songs to existing database in timely manner
2. To add lyrics for the songs.
3. To create default album playlists.
4. To create default movie playlists.
5. To create default singer playlists.
6. To modify and update existing playlists.
