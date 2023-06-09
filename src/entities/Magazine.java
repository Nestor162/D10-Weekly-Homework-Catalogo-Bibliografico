package entities;

public class Magazine extends Publication {
	public enum publicationFrequency {
		WEEKLY, MONTHLY, SEMIANNUAL
	}

	private publicationFrequency frequency;

	public Magazine(String ISBN, String title, int publicationYear,
			int numPages) {
		super(ISBN, title, publicationYear, numPages);

	}

	public Magazine(String ISBN, String title, int publicationYear,
			int numPages, publicationFrequency frequency) {

		super(ISBN, title, publicationYear, numPages);
		this.frequency = frequency;
	}

	public publicationFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(publicationFrequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "Magazine [frequency=" + frequency + ", ISBN=" + ISBN
				+ ", title=" + title + ", publicationYear=" + publicationYear
				+ ", numPages=" + numPages + "]" + System.lineSeparator();
	}

	@Override
	// Uso il formato CSV (comma separated values)
	public String toExportFormat() {
		return String.format("%s, %s,%s,%d,%d", "Magazine", title, ISBN,
				publicationYear, numPages);
	}

}
