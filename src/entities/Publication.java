package entities;

public abstract class Publication {
	protected String ISBN;
	protected String title;
	protected int publicationYear;
	protected int numPages;

	protected Publication(String ISBN, String title, int publicationYear,
			int numPages) {
		this.ISBN = ISBN;
		this.title = title;
		this.publicationYear = publicationYear;
		this.numPages = numPages;
	}

	public abstract String toExportFormat();

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}
