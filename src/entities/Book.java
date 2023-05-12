package entities;

public class Book extends Publication {
	private String author;
	private String genre;

	public Book(String ISBN, String title, int publicationYear, int numPages,
			String author, String genre) {
		super(ISBN, title, publicationYear, numPages);
		this.author = author;
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", genre=" + genre + ", ISBN=" + ISBN
				+ ", title=" + title + ", publicationYear=" + publicationYear
				+ ", numPages=" + numPages + "]" + System.lineSeparator();
	}

	@Override
	// Uso il formato CSV (comma separated values)
	public String toExportFormat() {
		return String.format("%s,%s,%s,%d,%d,%s,%s", "Book", title, ISBN,
				publicationYear, numPages, author, genre);
	}

}
