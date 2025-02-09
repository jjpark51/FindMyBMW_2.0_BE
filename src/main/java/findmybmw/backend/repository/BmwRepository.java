package findmybmw.backend.repository;

import findmybmw.backend.model.Bmw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmwRepository extends JpaRepository<Bmw, Long> {

    @Query("SELECT b.model FROM Bmw b")
    List<String> findAllModels();
    @Query("SELECT b FROM Bmw b WHERE b.model = :model")
    Bmw findByModel(String model);

    @Query("SELECT b.kaida FROM Bmw b")
    List<String> findAllKaidas();

    @Query("SELECT b.sales FROM Bmw b")
    List<Integer> findAllSales();

    @Query("SELECT b.price FROM Bmw b")
    List<Double> findAllPrices();

    @Query("SELECT b.xdrive FROM Bmw b")
    List<Double> findAllXdrives();

    @Query("SELECT b.weight FROM Bmw b")
    List<Double> findAllWeights();

    @Query("SELECT b.hb FROM Bmw b")
    List<Double> findAllHbs();

    @Query("SELECT b.cp FROM Bmw b")
    List<Double> findAllCps();

    @Query("SELECT b.sd FROM Bmw b")
    List<Double> findAllSds();

    @Query("SELECT b.wg FROM Bmw b")
    List<Double> findAllWgs();

    @Query("SELECT b.suv FROM Bmw b")
    List<Double> findAllSuvs();

    @Query("SELECT b.cv FROM Bmw b")
    List<Double> findAllCvs();

    @Query("SELECT b.engine1 FROM Bmw b")
    List<Double> findAllEngine1s();

    @Query("SELECT b.engine2 FROM Bmw b")
    List<Integer> findAllEngine2s();

    @Query("SELECT b.drange FROM Bmw b")
    List<Double> findAllDranges();

    @Query("SELECT b.phevfe FROM Bmw b")
    List<Double> findAllPhevfes();

    @Query("SELECT b.fe1 FROM Bmw b")
    List<Double> findAllFe1s();

    @Query("SELECT b.fe2 FROM Bmw b")
    List<Double> findAllFe2s();

    @Query("SELECT b.fe3 FROM Bmw b")
    List<Double> findAllFe3s();

    @Query("SELECT b.ftype FROM Bmw b")
    List<String> findAllFtypes();

    @Query("SELECT b.gasoline FROM Bmw b")
    List<Double> findAllGasolines();

    @Query("SELECT b.diesel FROM Bmw b")
    List<Double> findAllDiesels();

    @Query("SELECT b.phev FROM Bmw b")
    List<Double> findAllPhevs();

    @Query("SELECT b.ev FROM Bmw b")
    List<Double> findAllEvs();

    @Query("SELECT b.mseries FROM Bmw b")
    List<Double> findAllMseries();

    @Query("SELECT b.series FROM Bmw b")
    List<String> findAllSeries();

    @Query("SELECT b.ukl FROM Bmw b")
    List<Double> findAllUkls();

    @Query("SELECT b.kkl FROM Bmw b")
    List<Double> findAllKkls();

    @Query("SELECT b.mkl FROM Bmw b")
    List<Double> findAllMkls();

    @Query("SELECT b.gkl FROM Bmw b")
    List<Double> findAllGkls();

    @Query("SELECT b.leg FROM Bmw b")
    List<Double> findAllLegs();

    @Query("SELECT b.trunk1 FROM Bmw b")
    List<Double> findAllTrunk1s();

    @Query("SELECT b.trunk2 FROM Bmw b")
    List<Double> findAllTrunk2s();

    @Query("SELECT b.luxury FROM Bmw b")
    List<Double> findAllLuxurys();

    @Query("SELECT b.design FROM Bmw b")
    List<Integer> findAllDesigns();

    @Query("SELECT b.lwh FROM Bmw b")
    List<String> findAllLwhs();

    @Query("SELECT b.length FROM Bmw b")
    List<Double> findAllLengths();

    @Query("SELECT b.width FROM Bmw b")
    List<Double> findAllWidths();

    @Query("SELECT b.height FROM Bmw b")
    List<Double> findAllHeights();

    @Query("SELECT b.sound FROM Bmw b")
    List<Double> findAllSounds();

    @Query("SELECT b.ledk FROM Bmw b")
    List<Double> findAllLedks();

    @Query("SELECT b.enterp FROM Bmw b")
    List<Double> findAllEnterps();

    @Query("SELECT b.light FROM Bmw b")
    List<Double> findAllLights();

    @Query("SELECT b.comfortableInterior FROM Bmw b")
    List<Double> findAllComfortableInteriors();

    @Query("SELECT b.hud FROM Bmw b")
    List<Double> findAllHuds();

    @Query("SELECT b.driving FROM Bmw b")
    List<Double> findAllDrivings();

    @Query("SELECT b.parkingAssistance FROM Bmw b")
    List<Double> findAllParkingAssistances();

    @Query("SELECT b.drivingassistance FROM Bmw b")
    List<Double> findAllDrivingAssistances();
}
