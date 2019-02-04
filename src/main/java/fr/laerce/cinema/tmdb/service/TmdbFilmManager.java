package fr.laerce.cinema.tmdb.service;

import fr.laerce.cinema.tmdb.dao.TmdbFilmDao;
import fr.laerce.cinema.tmdb.model.TmdbFilm;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.zip.GZIPInputStream;

@Component
public class TmdbFilmManager {
    @Autowired
    private TmdbFilmDao tmdbFilmDao;

    public TmdbFilm getById(Long id) {
        return tmdbFilmDao.findById(id).get();
    }

    public List<TmdbFilm> getAll() {
        return tmdbFilmDao.findAllByOrderByIdAsc();
    }

    public String getFileName() {
        LocalDate date = LocalDate.now().minusDays(1);
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());
        return "movie_ids_" + month + "_" + day + "_" + year + ".json.gz";
    }
    public String getUrl() {
        String fileName = getFileName();
        String url = "http://files.tmdb.org/p/exports/" + fileName;
        return url;
    }
    public void importOnlineFullMovie_ids() {

     String url = getUrl();
        try {
            InputStream httpIS = new URL(url).openStream();
            InputStream gzipIS = new GZIPInputStream(httpIS);
            InputStream bufferedIS = new BufferedInputStream(gzipIS);

            //System.out.println(bufferedIS);

            BufferedReader br = new BufferedReader(new InputStreamReader(bufferedIS, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                String original_title = json.get("original_title").toString();
                Long id = json.getLong("id");
                double popularity = json.getDouble("popularity");
                TmdbFilm film = new TmdbFilm(id, original_title, popularity);
                tmdbFilmDao.save(film);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void importLocallyFullMovie_ids() {

        try {
            String fileName = getFileName();
            File inputFile = new File("C:\\Users\\CDI\\IdeaProjects\\cinemaVarni\\src\\main\\resources\\tmdbFile\\"+fileName);
            File outputFile = new File("C:\\Users\\CDI\\IdeaProjects\\cinemaVarni\\src\\main\\resources\\tmdbFile\\"+"tmdb.json");
            FileUtils.copyURLToFile(new URL(getUrl()),inputFile);
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(inputFile));
                FileOutputStream out = new FileOutputStream(outputFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = in.read(buffer)) != -1){
                        out.write(buffer, 0, len);
                    }
        } catch (IOException e) {
          // e.printStackTrace();
        }

    }


}

