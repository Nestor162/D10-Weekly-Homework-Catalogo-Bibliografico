package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;

import entities.Book;
import entities.Magazine;
import entities.Publication;

public class Main {

	public static void main(String[] args) {

		Publication book1 = new Book("9780765350381",
				"Mistborn: The Final Empire", 2006, 541, "Brandon Sanderson",
				"Fantasy Novel");

		Publication book2 = new Book("9780451524935", "1984", 1949, 328,
				"George Orwell", "Dystopian Novel");

		Publication book3 = new Book("9780553293357", "Foundation", 1951, 255,
				"Isaac Asimov", "Science Fiction Novel");

		Publication magazine1 = new Magazine("00062510", "National Geographic",
				2021, 126, Magazine.publicationFrequency.MONTHLY);

		Publication magazine2 = new Magazine("0040781X", "Time", 2022, 96,
				Magazine.publicationFrequency.WEEKLY);

		Publication magazine3 = new Magazine("01497939", "Scientific American",
				2021, 80, Magazine.publicationFrequency.MONTHLY);

		List<Publication> catalog = new ArrayList<>();

		addPublication(magazine1, catalog);
		addPublication(magazine2, catalog);
		addPublication(magazine3, catalog);
		addPublication(book1, catalog);
		addPublication(book2, catalog);
		addPublication(book3, catalog);

		removePublicationFromISBN(catalog, "01497939");
		removePublicationFromISBN(catalog, "00000000");

		ISBNSearch(catalog, "0040781X");

		yearSearch(catalog, 2006);

		authorSearch(catalog, "George Orwell");

		try {
			saveToFile(catalog, "catalog.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			loadFromFile("catalogLoad.txt");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// Funzione 1 - Aggiunta di un elemento
	public static void addPublication(Publication pub,
			List<Publication> catalog) {

		catalog.add(pub);

	}

	// Funzione 2 - Rimozione di un elemento dato un codice ISBN
	public static void removePublicationFromISBN(List<Publication> catalog,
			String queryISBN) {

		Predicate<Publication> isISBNequalToQuery = pub -> pub.getISBN()
				.equals(queryISBN);

		boolean removed = catalog.removeIf(isISBNequalToQuery);

		if (removed) {
			System.out.println("Pubblicazione con ISBN " + queryISBN
					+ " rimossa correttamente." + System.lineSeparator());
		} else {
			System.out.println("Nessuna pubblicazione trovata con ISBN "
					+ queryISBN + "." + System.lineSeparator());
		}

	}

	// Funzione 3 - Ricerca per ISBN
	public static void ISBNSearch(List<Publication> catalog, String queryISBN) {

		Predicate<Publication> isISBNequalToQuery = pub -> pub.getISBN()
				.equals(queryISBN);

		List<Publication> ISBNSearchResult = catalog.stream()
				.filter(isISBNequalToQuery).toList();

		System.out.println("Elementi trovati con il codice ISBN " + queryISBN
				+ ": " + System.lineSeparator() + ISBNSearchResult);
	}

	// Funzione 4 - Ricerca per anno pubblicazione
	public static void yearSearch(List<Publication> catalog, int queryYear) {

		Predicate<Publication> isYearEqualToQuery = pub -> pub
				.getPublicationYear() == queryYear;

		List<Publication> yearSearchResult = catalog.stream()
				.filter(isYearEqualToQuery).toList();

		System.out.println("Elementi trovati pubblicati nel anno " + queryYear
				+ ": " + System.lineSeparator() + yearSearchResult);
	}

	// Funzione 5 - Ricerca per autore
	public static void authorSearch(List<Publication> catalog,
			String queyAuthorName) {

		Predicate<Publication> isAuthorEqualToQuery = pub -> {

			if (pub instanceof Book) {
				Book book = (Book) pub;
				return book.getAuthor().equalsIgnoreCase(queyAuthorName);
			}
			return false;
		};

		List<Publication> authorSearchResult = catalog.stream()
				.filter(isAuthorEqualToQuery).toList();

		System.out.println("Elementi pubblicati da  " + queyAuthorName + ": "
				+ System.lineSeparator() + authorSearchResult);

	}

	// Funzione 6 - Salvataggio su disco dell'archivio
	public static void saveToFile(List<Publication> catalog, String fileName)
			throws IOException {
		File catalogFile = new File(fileName);

		StringBuilder sb = new StringBuilder();
		for (Publication publication : catalog) {
			sb.append(publication.toExportFormat())
					.append(System.lineSeparator());
		}

		FileUtils.write(catalogFile, sb.toString(), "UTF-8", false);
		System.out.println("Salvato il catalogo su file " + fileName
				+ System.lineSeparator());
	};

	// Funzione 7 - Caricamento dal disco dell'archivio
	public static void loadFromFile(String fileName) throws IOException {
		List<Publication> catalogLoaded = new ArrayList<>();
		File CatalogFile = new File(fileName);

		String content = FileUtils.readFileToString(CatalogFile, "UTF-8");
		String[] publications = content.split("\n");

		for (String publication : publications) {
			String[] attributes = publication.split(",");

			if (attributes[0].equals("Magazine")) {

				Magazine magazine = new Magazine(attributes[1], attributes[2],
						Integer.parseInt(attributes[3]),
						Integer.parseInt(attributes[4]));

				catalogLoaded.add(magazine);

			} else if (attributes[0].equals("Book")) {

				Book book = new Book(attributes[1], attributes[2],
						Integer.parseInt(attributes[3]),
						Integer.parseInt(attributes[4]), attributes[5],
						attributes[6]);

				catalogLoaded.add(book);
			}

		}
		System.out.println("Caricato il catalogo proveniente da " + fileName
				+ System.lineSeparator());

		System.out.println("Contenuto: " + System.lineSeparator()
				+ catalogLoaded + System.lineSeparator());

	}

}
