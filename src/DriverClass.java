import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DriverClass {

	public static Scanner sc = new Scanner(System.in);
	private static String adminUsername = "root";
	private static String adminPassword = "root";
	
	public void userMenu(UserLogin ul){
		boolean showMenu = true;
		String type = "user_playlist";
		System.out.println("\n\n\t\tHI " +ul.username.toUpperCase() + "!");
		
		while(showMenu){
			System.out.println("\n\t  USER MENU ");
			System.out.println("\t1. Create Playlist");
			System.out.println("\t2. Display your playlist");
			System.out.println("\t3. Play your playlist");
			System.out.println("\t4. Go back to main menu");
			System.out.println("\nPlease enter your choice : ");
			int choice = sc.nextInt();

			switch(choice){
			case 1:
				Playlist pl = Playlist.createPlaylist(ul,type);
				System.out.println("\nPlaylist successfully created !\n");
				showMenu = true;
				break;

			case 2:
				UserPlaylist.showDefaultPlaylist(ul);
				showMenu = true;
				break;
			
			case 3:
				UserPlaylist.showDefaultPlaylist(ul);
				Playlist playlist =new Playlist();
				System.out.println("\nPlease enter the playlist which you want to play :");
				int plyid = sc.nextInt();
				playlist.playPlaylist("user_playlist", plyid, ul);
				break;
			case 4:
				System.out.println("\nBYE "+ul.username +"!");
				System.out.println("\nGoing back to main menu ...");
				showMenu = false;
				break;
			}
		}
	}
		
	public void adminMenu(){
		boolean showMenu = true;
		while(showMenu){
			System.out.println("\n\t   Hi Admin!");
			System.out.println("\t 1. Create default playlist");
			System.out.println("\t 2. Display default playlist");
			System.out.println("\t 3. Go back to main menu");
			int choice = sc.nextInt();
			switch(choice){
			case 1:
				boolean flag = true;
				while(flag){
					System.out.println(" *** DEFAULT PLAYLIST ***");
					System.out.println("\nWhich type of default playlist do you want to create ?");
					System.out.println("\t 1. Album Playlist");
					System.out.println("\t 2. Singer Playlist");
					System.out.println("\t 3. Movie Playlist");
					System.out.println("\t 4. Back to admin menu");
					int subchoice = sc.nextInt();
					if(subchoice == 1){
						AlbumPlaylist albumPlaylist = AlbumPlaylist.createPlaylist();
						if(albumPlaylist != null){
							System.out.println("\n\n Album Playlist successfully created !");
							flag = false;
						}else{
							System.out.println("\n Playlist not created. Some error occured");
						}
					}else if(subchoice == 2){
						SingerPlaylist singerPlaylist = SingerPlaylist.createPlaylist();
						if(singerPlaylist != null){
							System.out.println("\n\n Singer Playlist successfully created !");
							flag = false;
						}else{
							System.out.println("\n Playlist not created. Some error occured");
						}
					}else if(subchoice == 3){
						MoviePlaylist moviePlaylist = MoviePlaylist.createPlaylist();
						if(moviePlaylist != null){
							System.out.println("\n\n Movie Playlist successfully created !");
							flag = false;
						}else{
							System.out.println("\n Playlist not created. Some error occured");
						}
					}else if(subchoice == 4){
						flag =false;
					}else{
						System.out.println("Invalid option. Please select the other option : ");
						flag = true;
					}
				}
				break;
			case 2:
				Playlist pl = new Playlist();
				pl.showDefaultPlaylist();
				break;
			case 3:
				showMenu = false;
				break;
			default:
				System.out.println("Invalid option. Please select some other option.");
				showMenu = true;
				break;
			}
		}
	}
	public static void main(String[] args) {
		UserLogin ul = null;
		boolean showMenu = true;
		DriverClass driverclass = new DriverClass();
		new Setup();
		
		System.out.println("\t\t*** Music Repository System ***");
		System.out.println("\t\t-------------------------------\n\n");
		
		while(showMenu){
			System.out.println("Welcome. If you are a registered user then LOGIN. Otherwise SIGNUP !");
			System.out.println("\t1. Login");
			System.out.println("\t2. SignUp");
			System.out.println("\t3. Search song");
			System.out.println("\t4. Lyrics for song");
			System.out.println("\t5. Play default playlist");
			System.out.println("\t6. Logout");
			System.out.println("\nPlease enter your choice : ");
			int option = sc.nextInt();
			
			switch(option){
			case 1:
				System.out.println("\n\t\t ~ LOGIN MENU ~ ");
				System.out.println("If you are registered user, please select User Login or else if you are Admin go with Admin Login. ");
				System.out.println("\t 1) User Login");
				System.out.println("\t 2) Admin Login");
				System.out.println("\nYour choice : ");
				int choice = DriverClass.sc.nextInt();
				
				if(choice == 1){
					ul = UserLogin.Login();
					if(ul != null){
						driverclass.userMenu(ul);
					}else{
						System.out.println("\nSorry! You are not registered user. Please signup! \n\n");
						showMenu = true;
					}
				}else if(choice == 2){
					boolean flag = true;
					while(flag){
						System.out.println("\nPlease enter admin credentials.");
						System.out.println("Admin username : ");
						String username = DriverClass.sc.next();
						if(username.equals(adminUsername)){
							System.out.println("Admin password : ");
							String password = DriverClass.sc.next();
							if(password.equals(adminPassword)){
								flag = false;
								break;
							}else{
								System.out.println("Incorrect password ! Please try again. \n");
								flag = true;
							}
							flag = false;
						}else{
							System.out.println("Incorrec username ! Please try again. \n");
							flag = true;
						}
					}
					
					if(!flag){
						driverclass.adminMenu();
					}
				}else{
					System.out.println("Invalid option. Please select some other oprion.");
					showMenu = true;
				}
				break;
				
			case 2:
				ul = UserLogin.Signup();
				if(ul != null){
					System.out.println("\nCongratulations! You have successfully signed up !");
					driverclass.userMenu(ul);
				}else{
					System.out.println("\nSorry! Some error occurred. Please try again later.");
					showMenu = true;
				}
				break;
				
			case 3:
				System.out.println("\n\t\t ~SEARCH SONG ~ \n");
				System.out.println("\nEnter song name or movie name : ");
				String query = sc.next();
				List<Song> results = Song.searchSong(query);
				if(results.size() >0 ){
					for(Song song : results){
						System.out.println(song.song_id + " "+ song.song_name);
					}
					System.out.println("\nSelect song number : ");
					int song_id = sc.nextInt();
					Song song = Setup.songsList.get(song_id-1);
					
					System.out.println("Yaay! What would you like to do?\n");
					
					System.out.println("1. Play Song");
					System.out.println("2. Go back to main menu.");
					System.out.println("Please enter your choice : ");
					int opt = sc.nextInt();

					if(opt == 1){
						System.out.println("Do you want lyrics of the song ? (yes/no)");
						String show = sc.next();
						if(show.equals("yes")){
							if(Setup.LyricsList.containsKey(song.song_name)){
								System.out.println("\nLyrics for your Song - ");
								Lyrics.getLyricsforSong(song.song_name);
								System.out.println("\n\n");
							}else{
								System.out.println("\nSorry we do not have lyrics for this song :(");
							}
						}
						Player.loadPlayer(song.songFile);
					}if(opt == 2){
						showMenu = true;
					}
				}else{
					System.out.println("Sorry! Song not found. ");
					showMenu = true;
					break;
				}
				
				break;
				
			case 4:
				System.out.println("\t\t SONGS ");
				for(Song s : Setup.songsList){
					System.out.println("\t" + s.song_id + " - " +s.song_name);
				}
				System.out.println("Please enter the song number for lyrics : ");
				int songno = sc.nextInt();
				Song song = Setup.songsList.get(songno-1);
				if(Setup.LyricsList.containsKey(song.song_name)){
					System.out.println("\nLyrics for your Song - ");
					Lyrics.getLyricsforSong(song.song_name);
				}else{
					System.out.println("\nSorry we do not have lyrics for this song :(");
				}
				System.out.println("\n\n");
				break;
				
			case 5:
				boolean notpresent = false;
				Playlist playlist = new Playlist();
				System.out.println("\n\t\t ~ DEFAULT PLAYLISTS ~");
				System.out.println("\nWhich type of playlist do you want to play");
				System.out.println("\t 1. Album Playlist");
				System.out.println("\t 2. Singer Playlist");
				System.out.println("\t 3. Movie Playlist");
				System.out.println("\t 4. Back to admin menu");
				System.out.println("\nPlease enter your choice : ");
				int choice1 = sc.nextInt();
				if(choice1 == 1){
					AlbumPlaylist al = new AlbumPlaylist();
					al.showDefaultPlaylist();
					if(Setup.albumPlayLists.size() == 0)
						notpresent = true;
				}else if(choice1 == 2){
					SingerPlaylist sl = new SingerPlaylist();
					sl.showDefaultPlaylist();
					if(Setup.singerPlayLists.size() == 0)
						notpresent = true;
				}else if(choice1 == 3){
					MoviePlaylist ml = new MoviePlaylist();
					ml.showDefaultPlaylist();
					if(Setup.moviePlayLists.size() == 0)
						notpresent = true;
				}
				if(!notpresent){
					System.out.println("\nPlease enter the playlist which you want to play :");
					int choice2 = sc.nextInt();
					if(choice1==3){
						playlist.playPlaylist("movie", choice2);
					}
				}
				showMenu = true;
				break;
			case 6:
				System.out.println("\nLogging out ....... Bye !");
				System.exit(0);
			default:
				System.out.println("Invalid option. Please select some other option : ");
				showMenu = true;
				break;
			}	
		}
	}
	
	
}

//database
class Setup {
	
	 UserLogin[] users = new UserLogin[2];
	 Song[] songs = new Song[6];
	 Movie[] movies = new Movie[3];
	 Lyrics[] lyrics = new Lyrics[6];
	 Playlist[] playlists = new Playlist[3];
	 static List<UserLogin> userLoginList = new ArrayList<UserLogin>();
	 static HashMap<Integer,UserLogin> userLoginMap = new HashMap<Integer,UserLogin>();
	 static List<Song> songsList = new ArrayList<Song>();
	 static List<Movie> moviesList = new ArrayList<Movie>();
	 static HashMap<UserLogin,List<Playlist>> userplaylist = new HashMap<UserLogin,List<Playlist>>();
	 static List<AlbumPlaylist> albumPlayLists = new ArrayList<AlbumPlaylist>();
	 static List<MoviePlaylist> moviePlayLists = new ArrayList<MoviePlaylist>();
	 static List<SingerPlaylist> singerPlayLists = new ArrayList<SingerPlaylist>();
	 static HashMap<String,Lyrics> LyricsList = new HashMap<String, Lyrics>();
	 
	 public Setup(){
		 users[0] = new UserLogin(1,"Tom","tom123");
		 users[1] = new UserLogin(2,"Jerry","jerry123");
		 userLoginMap.put(1, users[0]);
		 userLoginMap.put(2, users[1]);
		 userLoginList.add(users[0]);
		 userLoginList.add(users[1]);
		 
		 songs[0] = new Song(1,1,"Jeete Hain Chal","Neerja","Neerja/01 Jeete Hain Chal.mp3");
		 songs[1] = new Song(2,1,"Gehra Ishq","Neerja","Neerja/03 Gehra Ishq.mp3");
		 songs[2] = new Song(3,2,"Matargashti","Tamasha","Tamasha/ Matargashti.mp3");
		 songs[3] = new Song(4,2,"Tum Saath Ho","Tamasha","Tamasha/Tum Saath Ho.mp3");
		 songs[4] = new Song(5,3,"Soch Na Sake","Airlift","Airlift/Soch Na Sake.mp3");
		 songs[5] = new Song(6, 3, "Dil Cheez Tujhe Dedi", "Airift", "Airlift/Dil Cheez Tujhe Dedi.mp3");
		 songsList.add(songs[0]);
		 songsList.add(songs[1]);
		 songsList.add(songs[2]);
		 songsList.add(songs[3]);
		 songsList.add(songs[4]);
		 songsList.add(songs[5]);
		 
		 List<Song> listofSongs = new ArrayList<Song>();
		 listofSongs.add(songs[0]);
		 listofSongs.add(songs[1]);
		 movies[0] = new Movie(1,"Neerja",  listofSongs);
		 listofSongs = new ArrayList<Song>();
		 listofSongs.add(songs[2]);
		 listofSongs.add(songs[3]);
		 movies[1] = new Movie(2, "Tamasha", listofSongs);
		 listofSongs = new ArrayList<Song>();
		 listofSongs.add(songs[4]);
		 listofSongs.add(songs[5]);
		 movies[2] = new Movie(3, "Airlift", listofSongs);
		 
		 moviesList.add(movies[0]);
		 moviesList.add(movies[1]);
		 moviesList.add(movies[2]);
		
		 MoviePlaylist moviePlaylist = new MoviePlaylist();
		 List<Song> songList = new ArrayList<Song>();
		 songList.add(songs[0]);
		 songList.add(songs[1]);
		 Playlist pl = new Playlist(1, "Neeraja", "movie",0, songList);
		 moviePlaylist.movie_name = pl.playlist_name;
		 moviePlaylist.listofSongs = pl.listofSongs;
		 moviePlaylist.movie_id = Setup.moviePlayLists.size()+1;
		 Setup.moviePlayLists.add(moviePlaylist);
		 
		 moviePlaylist = new MoviePlaylist();
		 songList = new ArrayList<Song>();
		 songList.add(songs[2]);
		 songList.add(songs[3]);
		 pl = new Playlist(2, "Tamasha", "movie",0, songList);
		 moviePlaylist.movie_name = pl.playlist_name;
		 moviePlaylist.listofSongs = pl.listofSongs;
		 moviePlaylist.movie_id = Setup.moviePlayLists.size()+1;
		 Setup.moviePlayLists.add(moviePlaylist);
		 
		 moviePlaylist = new MoviePlaylist();
		 songList = new ArrayList<Song>();
		 songList.add(songs[4]);
		 songList.add(songs[5]);
		 pl = new Playlist(3, "Airlift", "movie",0, songList);
		 moviePlaylist.movie_name = pl.playlist_name;
		 moviePlaylist.listofSongs = pl.listofSongs;
		 moviePlaylist.movie_id = Setup.moviePlayLists.size()+1;
		 Setup.moviePlayLists.add(moviePlaylist);
		 
		 Lyrics lyrics = new Lyrics(1, 1, 1, "Lyrics/Jeete Hain Chal");
		 LyricsList.put("Jeete Hain Chal", lyrics);
		 lyrics = new Lyrics(2, 2, 1, "Lyrics/Gehra Ishq");
		 LyricsList.put("Gehra Ishq", lyrics);
		 lyrics = new Lyrics(3, 1, 2, "Lyrics/Matargashti");
		 LyricsList.put("Matargashti", lyrics);
		 lyrics = new Lyrics(4, 2, 2, "Lyrics/Tum Saath Ho");
		 LyricsList.put("Tum Saath Ho", lyrics);
		 lyrics = new Lyrics(5, 1, 3, "Lyrics/Soch Na Sake");
		 LyricsList.put("Soch Na Sake" ,lyrics);
		 lyrics = new Lyrics(6, 2, 3, "Lyrics/Dil Cheez Tujhe Dedi");
		 LyricsList.put("Dil Cheez Tujhe Dedi", lyrics);	
		 
	 }
}

//@Invariant({user_id>0, "username" != null, "password" != null})
class UserLogin{
	int user_id;
	String username;
	String password;
	
	public UserLogin(int user_id, String username, String password){
		this.user_id = user_id;
		this.username = username;
		this.password = password;
	}

	public UserLogin() {
	}

	public static UserLogin Login(){
		UserLogin ul = new UserLogin();
		System.out.println("\n\t\t~~LOGIN~~ \n");
		System.out.println("Username : ");
		ul.username = DriverClass.sc.next();
		System.out.println("Password : ");
		ul.password = DriverClass.sc.next();
				
		for(UserLogin uLogin : Setup.userLoginList){
			if(uLogin.username.equals(ul.username) && uLogin.password.equals(ul.password)){
				return uLogin;
			}
		}
		return null;
	}

//	@Requires({"username"})
//	@Ensures({"username present or not"})
	public boolean checkifUsernameIsPresent(String username){
		for(UserLogin ulogin : Setup.userLoginList){
			if(ulogin.username.equalsIgnoreCase(username)){
				return true;
			}
		}
		return false;
	}
	
	public static UserLogin Signup(){
		UserLogin ul = new UserLogin();
		System.out.println("\n ~~SIGNUP~~ \n");
		System.out.println("Enter Username : ");
		boolean flag = false;
		do{
			ul.username = DriverClass.sc.next();
			boolean isPresent = ul.checkifUsernameIsPresent(ul.username);
			if(isPresent){
				System.out.println("Username already present. Enter new username : ");
				flag = true;
			}else{
				flag=false;
			}
		}while(flag);
		
		System.out.println("Enter Password : ");
		ul.password = DriverClass.sc.next();
		ul.user_id = Setup.userLoginList.size();
		Setup.userLoginList.add(ul);
		return ul;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}

//@Invariant({"song_id > 0", "song_name != null", "songFile is present"})
class Song{
	int song_id;
	int movie_id;
	String song_name;
	String movie_name;
	String songFile;
	
	Song(){
	}
	
	public Song(int song_id, int movie_id, String song_name, String movie_name, String songFile){
		this.song_id = song_id;
		this.movie_id = movie_id;
		this.song_name = song_name;
		this.movie_name = movie_name;
		this.songFile = songFile;
	}
	
	public Song getSong(int song_id){
		Song s;
		s = Setup.songsList.get(song_id-1);
		return s;
	}
	
	public static List<Song> searchSong(String query){
		List<Song> songList = new ArrayList<Song>();
		for(Song s : Setup.songsList){
			if(s.song_name.equalsIgnoreCase(query) || s.song_name.toLowerCase().contains(query.toLowerCase())){
				songList.add(s);
			}
		}
		
		Movie m =new Movie();
		songList.addAll(m.getSongsforMovie(query));
		return songList;
	}
}

//@Invariant({" movie_id > 0", "movie_name != null"})
class Movie{
	int movie_id;
	String movie_name;
	List<Song> listofSongs;
	
	public Movie(int movie_id, String movie_name, List<Song> listofSongs){
		this.movie_id = movie_id;
		this.movie_name = movie_name;

		this.listofSongs = listofSongs;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

//	@Requires({"lquery"})
//	@Ensures({"songList>0"})
	public List<Song> getSongsforMovie(String query){
		List<Song> songList = new ArrayList<Song>();
		for(Movie m : Setup.moviesList){
			if(m.movie_name.equalsIgnoreCase(query) || m.movie_name.toLowerCase().contains(query.toLowerCase())){
				for(Song s : m.listofSongs){
					songList.add(s);
				}
			}
		}
		return songList;
	}
}

//@Invariant({"lyrics_id > 0","lyricsText is present"})
class Lyrics{
	int lyrics_id;
	int song_id;
	int movie_id;
	String lyricsText;
	
	public Lyrics(int lyrics_id, int song_id, int movie_id, String lyricsText){
		this.lyrics_id = lyrics_id;
		this.song_id = song_id;
		this.movie_id = movie_id;
		this.lyricsText = lyricsText;
	}
	
//	@Requires({"song_name"})
//	@Ensures({"lyrics of song printed"})
	public static void getLyricsforSong(String song_name){
		String line ="";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("Lyrics/"+song_name)));
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

// @Invariant({"type != null"})
class Playlist{
	int playlist_id = 1;
	String playlist_name;
	static String type;
	int user_id;
	List<Song> listofSongs;

	public Playlist(int playlist_id, String playlist_name, String type, int user_id, List<Song> listofSongs) {
		this.playlist_id = playlist_id;
		this.playlist_name = playlist_name;
		this.type = type;
		this.user_id = user_id;
		this.listofSongs = listofSongs;
	}

	public Playlist() {
		// TODO Auto-generated constructor stub
	}

	public static Playlist createPlaylist(String type){
		return new Playlist().createPlaylist(null,type);
	}
	
	public void showDefaultPlaylist(){
		System.out.println("\n\t\tS *** DEFAULT PLAYLIST ***");
		AlbumPlaylist al = new AlbumPlaylist();
		al.showDefaultPlaylist();
		SingerPlaylist sl = new SingerPlaylist();
		sl.showDefaultPlaylist();
		MoviePlaylist ml = new MoviePlaylist();
		ml.showDefaultPlaylist();
	}
	
//	@Requires({"listofSongs.size() == 0"})
//	@Ensures({"playlist != 0", "listofSongs.size() > 0"})
	public static Playlist createPlaylist(UserLogin ul, String type){

		int user_id = 0;
		if(ul == null){
			user_id = 0;
		}else
			user_id = ul.user_id;
		int playlist_id = 1;
		System.out.println("\nEnter playlist name : ");
		String playlist_name = new Scanner(System.in).next();
		System.out.println("\n\t\t LIST OF SONGS");
		for(Song song : Setup.songsList){
			System.out.println("\t" + song.song_id + " - "+ song.song_name);
		}
		List<Song> listofSongs = new ArrayList<Song>();
		System.out.println("Enter song numbers which are to be added in the playlist (comma between numbers)");
		String strSongNo = new Scanner(System.in).next();
		String[] strSongArr = strSongNo.split(",");
		for(String s : strSongArr){
			Song song = Setup.songsList.get(Integer.parseInt(s)-1);
			listofSongs.add(song);
		}
		
		Playlist playlist = new Playlist(playlist_id, playlist_name, type, user_id, listofSongs);
		if(ul!=null){
			if(Setup.userplaylist.containsKey(ul)){
				List<Playlist> user_playlist = Setup.userplaylist.get(ul);
				playlist.playlist_id = user_playlist.size()+1;
				user_playlist.add(playlist);
			}else{
				List<Playlist> user_playlist = new ArrayList<Playlist>();
				playlist.playlist_id = user_playlist.size()+1;
				user_playlist.add(playlist);
				Setup.userplaylist.put(ul, user_playlist);
			}
		}
		return playlist;
	}

//	@Requires({"user_id != 0"})
//	@Ensures({"s.getValue().size > 0"})
	public static List<Playlist> getPlaylistofUser(int user_id){
		for(Entry<UserLogin, List<Playlist>> s: Setup.userplaylist.entrySet()){
			UserLogin key = s.getKey();
			if(key.user_id == user_id){
				return s.getValue();
			}
		}
		return null;
	}
	
	public void playPlaylist(String type, int playlist_id){
		playPlaylist(type, playlist_id, null);
	}
		
	public void playPlaylist(String type, int playlist_id, UserLogin ul){
		List<Song> playSongs = null;
		if(type.equals("movie")){
			MoviePlaylist ml = Setup.moviePlayLists.get(playlist_id-1);
			playSongs = ml.listofSongs;
		}else if(type.equals("album")){
			AlbumPlaylist al = Setup.albumPlayLists.get(playlist_id-1);
			playSongs = al.listofSongs;
		}else if(type.equals("movie")){
			MoviePlaylist ml = Setup.moviePlayLists.get(playlist_id-1);
			playSongs = ml.listofSongs;
		}else if(type.equals("user_playlist")){
			List<Playlist> pl = Setup.userplaylist.get(ul);
			Playlist userP = pl.get(playlist_id-1);
			playSongs = userP.listofSongs;
		}
		boolean flag = true;
		for(Song s : playSongs){
			Player.loadPlayer(s.songFile);
			while(flag){
				System.out.println("\nEnter next to play next song ..");
				String next = DriverClass.sc.next();
				if("next".equals(next)){
					flag = false;
				}else{
					System.out.println("\n Invalid input !!");
					flag = true;
				}
			}
		}

	}
	
}

//@Invariant({"album_id>0, pl!=null"})
class AlbumPlaylist extends Playlist{
	int album_id = 1;
	String album_name;
	Playlist pl;
	
	public AlbumPlaylist() {
		type = "album";
	}
	
	public void showDefaultPlaylist(){
		System.out.println("\n\t~~ Album Playlist ~~ ");
		if(Setup.albumPlayLists.size() > 0){
			System.out.println("\n");
			for(AlbumPlaylist al : Setup.albumPlayLists){
				if(al!=null){
					System.out.println("\t\t"+al.album_id + " " + al.album_name);
					for(Song song : al.pl.listofSongs){
						System.out.println("\t\t\t"+song.song_id + " " + song.song_name);
					}
				}
			}
		}else{
			System.out.println("\nOops! There are no album playlists saved right now.");
		}
	}
	public static AlbumPlaylist createPlaylist(){
		
		AlbumPlaylist albumPlaylist = new AlbumPlaylist();
		if(Setup.albumPlayLists.size() == 0){
			albumPlaylist.album_id = 1;
		}else{
			albumPlaylist.album_id = Setup.albumPlayLists.size();
		}
		albumPlaylist.pl = Playlist.createPlaylist(type);
		albumPlaylist.album_name = albumPlaylist.pl.playlist_name;
		Setup.albumPlayLists.add(albumPlaylist);
		return albumPlaylist;	
	}
	
//	@Requires({"album_id != 0"})
//	@Ensures({"albumPlaylist != null"})
	public static AlbumPlaylist getAlbumDetails(int album_id){
		AlbumPlaylist albumPlaylist = new AlbumPlaylist();
		albumPlaylist = Setup.albumPlayLists.get(album_id+1);
		return albumPlaylist;
	}
}

//@Invariant({"singer_id>0, pl!=null"})
class SingerPlaylist extends Playlist{
	int singer_id = 1;
	String singer_name;
	Playlist pl;
	
	public SingerPlaylist() {
		type = "singer";
	}
	
	public void showDefaultPlaylist(){
		System.out.println("\n\t~~ Singer Playlist ~~ ");
		if(Setup.singerPlayLists.size() > 0){
			System.out.println("\n");
			for(SingerPlaylist sl : Setup.singerPlayLists){
				if(sl!=null){
					System.out.println("\t\t"+sl.singer_id + " " + sl.singer_name);
					for(Song song : sl.pl.listofSongs){
						System.out.println("\t\t\t"+song.song_id + " " + song.song_name);
					}
				}
			}
		}else{
			System.out.println("Oops! There are no singer playlists saved right now.");
		}
	}
	
	public static SingerPlaylist createPlaylist(){
		
		SingerPlaylist singerPlaylist = new SingerPlaylist();
		if(Setup.singerPlayLists.size() == 0){
			singerPlaylist.singer_id = 1;
		}else{
			singerPlaylist.singer_id = Setup.singerPlayLists.size();
		}
		singerPlaylist.pl = Playlist.createPlaylist(type);
		singerPlaylist.singer_name = singerPlaylist.pl.playlist_name;
		Setup.singerPlayLists.add(singerPlaylist);
		return singerPlaylist;	
	}
	
//	@Requires({"singer_id != 0"})
//	@Ensures({"singerPlaylist != null"})
	public SingerPlaylist getSingerDetails(int singer_id){
		SingerPlaylist singerPlaylist = new SingerPlaylist();
		singerPlaylist = Setup.singerPlayLists.get(singer_id+1);
		return singerPlaylist;
	}
}

//@Invariant({"movie_id>0, pl!=null"})
class MoviePlaylist extends Playlist{
	int movie_id = 1;
	String movie_name;
	Playlist pl;
	
	public MoviePlaylist() {
		type = "movie";
	}
	
	public void showDefaultPlaylist(){
		System.out.println("\n\t~~ Movie Playlist ~~ ");
		if(Setup.moviePlayLists.size() > 0){
			System.out.println("\n");
			for(MoviePlaylist ml : Setup.moviePlayLists){
				if(ml!=null){
					System.out.println("\t\t"+ml.movie_id + " " + ml.movie_name);
					for(Song song : ml.listofSongs){
						System.out.println("\t\t\t"+song.song_id + " " + song.song_name);
					}
				}
			}
		}else{
			System.out.println("Oops! There are no movie playlists saved right now.");
		}
	}
	
	public static MoviePlaylist createPlaylist(){
		
		MoviePlaylist moviePlaylist = new MoviePlaylist();
		if(Setup.moviePlayLists.size() == 0){
			moviePlaylist.movie_id = 1;
		}else{
			moviePlaylist.movie_id = Setup.moviePlayLists.size();
		}
		moviePlaylist.pl = Playlist.createPlaylist(type);
		moviePlaylist.movie_name = moviePlaylist.pl.playlist_name;
		Setup.moviePlayLists.add(moviePlaylist);
		return moviePlaylist;	
	}
	
//	@Requires({"movie_id != 0"})
//	@Ensures({"moviePlaylist != null"})
	public MoviePlaylist getMovieDetails(int movie_id){
		MoviePlaylist moviePlaylist = new MoviePlaylist();
		moviePlaylist = Setup.moviePlayLists.get(movie_id+1);
		return moviePlaylist;
	}
}

//@Invariant({"user_id>0"})
class UserPlaylist extends Playlist{
	int user_id;
	
//	@Requires({"UserLogin ul"})
//	@Ensures({"playlist!=0"})
	public static void showDefaultPlaylist(UserLogin ul){
		System.out.println("\n\t\t MY PLAYLISTS ");
		List<Playlist> playList = Playlist.getPlaylistofUser(ul.user_id);
		if(playList!=null){
			int i = 1;
			for(Playlist p : playList){
				System.out.println("\t " +p.playlist_id+ " - " + p.playlist_name);
				for(Song song : p.listofSongs){
					System.out.println("\t"+ song.song_name);
				}
			}
		}else{
			System.out.println("Oops! You don't have any playlist saved!");
		}
	}
}

class Player{
	AdvancedPlayer playMP3 = null;
     class PlayThread extends Thread
	     {
	     	boolean hold=true;	//know whether or not to pause the thread
	
	     	public void run()
	    	{
	    	    try {
	    	    	while (true)
	    	    	{
	    	    		synchronized (this)
	    	    		{
	    	    			while (hold)
								try {
									wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
	    	    		}
	    	    		if (playMP3!= null)
	    	    			playMP3.play();
	    	    	}
				} catch (JavaLayerException e) {
					e.printStackTrace();
					
				}
	    	}
	    	
	    	public void setMP3()
	    	{
	    		try {	
	    			synchronized (this)
	    			{
	    				hold = false;
	    				notify();
	    			}

	    		} catch (Exception e) {
					e.printStackTrace();
				}

	    	}
	    	
	    	@Override
			public void interrupt()
	    	{
	    		if (playMP3 != null)
	    			playMP3.close();
	    		super.interrupt();
	    		playThread = null;
	    	}
	     }
	        
    private static PlayThread playThread;

    public Player(String songFile){
    	if (playThread == null)
    	{
    		playThread = new PlayThread();
    		playThread.start();
    	}  
    	try{
    		FileInputStream fis = new FileInputStream(songFile);
    		playMP3  = new javazoom.jl.player.advanced.AdvancedPlayer(fis);
    		System.out.println("\nPlaying..... Enjoy the song!");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
 	  public void stop()
 	    {
 	    	playThread.interrupt();
 	    }
 	  
 	 //@Requires({"songFile is present"})
 	//@Ensures({"song stops playing "})  
	public static void loadPlayer(String songFile){
	 	Scanner sc = new Scanner(System.in);
	 	boolean flag = true;
		try{
			System.out.println("\n\t "+songFile);
			Player p = new Player(songFile);
			playThread.setMP3();

		 	while(flag){
		 		System.out.println("\nTo stop the song please type stop");
			 	String stop = sc.next();
		 		if("stop".equalsIgnoreCase(stop)){
		 			flag = false;
		 			p.stop();
		 		}
		 		else{
		 			System.out.println("\nInvalid input.");
		 			flag =true;
		 		}
		 	}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}