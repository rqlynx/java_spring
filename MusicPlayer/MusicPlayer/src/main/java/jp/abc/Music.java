package jp.abc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column(length = 200, nullable = false)
	@NotEmpty
	private String title;

	@Column(length = 200, nullable = false)
	@NotEmpty
	private String artist;

	@ManyToOne
	private PlayList playlist;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + "]";
	}

	public PlayList getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlayList playlist) {
		this.playlist = playlist;
	}

}