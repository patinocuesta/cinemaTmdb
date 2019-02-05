package online.patino.cinema.tmdb.service;

import online.patino.cinema.tmdb.dao.TmdbPersonDao;
import online.patino.cinema.tmdb.model.TmdbPerson;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.zip.GZIPInputStream;

@Component
public class TmdbPersonManager {
    @Autowired
    private TmdbPersonDao tmdbPersonDao;

    public TmdbPerson getById(Long id) {
        return tmdbPersonDao.findById(id).get();
    }

    public Iterable<TmdbPerson> getAll() {return tmdbPersonDao.findAll();}

    public String getFileName() {
        LocalDate date = LocalDate.now().minusDays(1);
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());
        return "person_ids_" + month + "_" + day + "_" + year + ".json.gz";
    }
    public String getUrl() {
        String fileName = getFileName();
        String url = "http://files.tmdb.org/p/exports/" + fileName;
        return url;
    }

    public void importOnlineFullPerson_ids() {

        String url = getUrl();
        try {
            InputStream httpIS = new URL(url).openStream();
            InputStream gzipIS = new GZIPInputStream(httpIS);
            InputStream bufferedIS = new BufferedInputStream(gzipIS);
            BufferedReader br = new BufferedReader(new InputStreamReader(bufferedIS, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                Long id = json.getLong("id");
                Double popularity = json.getDouble("popularity");
                Boolean adult= json.getBoolean("adult");
                String name= json.getString("name");
                TmdbPerson person = new TmdbPerson(id, name, popularity,adult);
                tmdbPersonDao.save(person);
            }
        } catch (IOException | JSONException e) {e.printStackTrace();}
    }
    public void importLocallyFullPerson_ids() {

        try {
            String fileName = getFileName();
            File inputFile = new File("C:\\Users\\CDI\\IdeaProjects\\cinemaVarni\\src\\main\\resources\\tmdbFile\\"+fileName);
            //File inputFile = new File("C:\\Users\\dpc-c\\Documents\\Java_Varni_Ex\\cinemaTmdb\\src\\main\\resources\\tmdbFile\\"+fileName);
            //File outputFile = new File("C:\\Users\\dpc-c\\Documents\\Java_Varni_Ex\\cinemaTmdb\\src\\main\\resources\\tmdbFile\\"+"tmdb.json");
            File outputFile = new File("C:\\Users\\CDI\\IdeaProjects\\cinemaVarni\\src\\main\\resources\\tmdbFile\\"+"tmdbPersons.json");
            FileUtils.copyURLToFile(new URL(getUrl()),inputFile);
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(inputFile));
            FileOutputStream out = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            InputStream inputStream = new FileInputStream(outputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            //Reader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject json = new JSONObject(line);
                Long id = json.getLong("id");
                Double popularity = json.getDouble("popularity");
                Boolean adult= json.getBoolean("adult");
                String name= json.getString("name");
                TmdbPerson person = new TmdbPerson(id, name, popularity,adult);
                tmdbPersonDao.save(person);
            }
        } catch (IOException | JSONException e) {e.printStackTrace();}

    }

}
