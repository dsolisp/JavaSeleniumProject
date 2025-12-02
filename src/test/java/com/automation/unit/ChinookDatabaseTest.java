package com.automation.unit;

import com.automation.utils.SqlConnection;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests for Chinook sample database.
 * Uses the same chinook.db as Python project.
 */
@Epic("Database Testing")
@Feature("Chinook Database")
@DisplayName("Chinook Database Tests")
class ChinookDatabaseTest {

    private static final String CHINOOK_DB = "src/main/resources/data/chinook.db";
    private Connection conn;

    @BeforeEach
    void setUp() throws Exception {
        conn = SqlConnection.getConnection(CHINOOK_DB);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    @Story("Connection")
    @Description("Should connect to Chinook database")
    @DisplayName("Should connect to chinook.db")
    void shouldConnectToChinookDatabase() {
        assertThat(conn).isNotNull();
    }

    @Test
    @Story("Tables")
    @Description("Chinook database should have expected tables")
    @DisplayName("Should have expected tables")
    void shouldHaveExpectedTables() throws Exception {
        List<String> tables = SqlConnection.getTableNames(conn);
        
        assertThat(tables).contains(
                "albums", "artists", "customers", "employees",
                "genres", "invoices", "invoice_items",
                "media_types", "playlists", "playlist_track", "tracks"
        );
    }

    @Test
    @Story("Query Artists")
    @Description("Should query artists table")
    @DisplayName("Should query artists")
    void shouldQueryArtists() throws Exception {
        List<Map<String, Object>> artists = SqlConnection.fetchAll(conn,
                "SELECT * FROM artists LIMIT 5");
        
        assertThat(artists).hasSize(5);
        assertThat(artists.get(0)).containsKeys("ArtistId", "Name");
    }

    @Test
    @Story("Query Artists")
    @Description("Should find specific artist by name")
    @DisplayName("Should find artist by name")
    void shouldFindArtistByName() throws Exception {
        Map<String, Object> artist = SqlConnection.fetchOne(conn,
                "SELECT * FROM artists WHERE Name LIKE ?", "%AC/DC%");
        
        assertThat(artist).isNotNull();
        assertThat(artist.get("Name")).isEqualTo("AC/DC");
    }

    @Test
    @Story("Query Albums")
    @Description("Should query albums with artist join")
    @DisplayName("Should join albums and artists")
    void shouldJoinAlbumsAndArtists() throws Exception {
        List<Map<String, Object>> albums = SqlConnection.fetchAll(conn,
                """
                SELECT a.Title, ar.Name as Artist
                FROM albums a
                JOIN artists ar ON a.ArtistId = ar.ArtistId
                LIMIT 10
                """);
        
        assertThat(albums).hasSize(10);
        assertThat(albums.get(0)).containsKeys("Title", "Artist");
    }

    @Test
    @Story("Query Tracks")
    @Description("Should query tracks with all related info")
    @DisplayName("Should query tracks with album and artist")
    void shouldQueryTracksWithDetails() throws Exception {
        List<Map<String, Object>> tracks = SqlConnection.fetchAll(conn,
                """
                SELECT t.Name as Track, a.Title as Album, ar.Name as Artist, g.Name as Genre
                FROM tracks t
                JOIN albums a ON t.AlbumId = a.AlbumId
                JOIN artists ar ON a.ArtistId = ar.ArtistId
                JOIN genres g ON t.GenreId = g.GenreId
                WHERE g.Name = ?
                LIMIT 5
                """, "Rock");
        
        assertThat(tracks).hasSizeGreaterThan(0);
        assertThat(tracks.get(0)).containsEntry("Genre", "Rock");
    }

    @Test
    @Story("Aggregation")
    @Description("Should count records in tables")
    @DisplayName("Should count table records")
    void shouldCountTableRecords() throws Exception {
        Map<String, Object> result = SqlConnection.fetchOne(conn,
                "SELECT COUNT(*) as count FROM tracks");
        
        Number count = (Number) result.get("count");
        assertThat(count.intValue()).isGreaterThan(0);
    }

    @Test
    @Story("Aggregation")
    @Description("Should calculate invoice totals")
    @DisplayName("Should calculate invoice totals")
    void shouldCalculateInvoiceTotals() throws Exception {
        List<Map<String, Object>> invoices = SqlConnection.fetchAll(conn,
                """
                SELECT c.FirstName, c.LastName, SUM(i.Total) as TotalSpent
                FROM invoices i
                JOIN customers c ON i.CustomerId = c.CustomerId
                GROUP BY c.CustomerId
                ORDER BY TotalSpent DESC
                LIMIT 5
                """);
        
        assertThat(invoices).hasSize(5);
        Number topSpender = (Number) invoices.get(0).get("TotalSpent");
        assertThat(topSpender.doubleValue()).isGreaterThan(0);
    }
}

