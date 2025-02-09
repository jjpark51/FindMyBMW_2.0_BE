package findmybmw.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bmw_data")  // Add this line to specify the table name
@Data
public class Bmw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "KAIDA")
    private String kaida;

    @Column(name = "SALES")
    private Integer sales;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "XDRIVE")
    private Integer xdrive;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "HB")
    private Integer hb;

    @Column(name = "CP")
    private Integer cp;

    @Column(name = "SD")
    private Integer sd;

    @Column(name = "WG")
    private Integer wg;

    @Column(name = "SUV")
    private Integer suv;

    @Column(name = "CV")
    private Integer cv;

    @Column(name = "ENGINE1")
    private Integer engine1;

    @Column(name = "ENGINE2")
    private Integer engine2;

    @Column(name = "DRANGE")
    private Integer drange;

    @Column(name = "PHEVFE")
    private Integer phevfe;

    @Column(name = "FE1")
    private Double fe1;

    @Column(name = "FE2")
    private Double fe2;

    @Column(name = "FE3")
    private Double fe3;

    @Column(name = "FTYPE")
    private String ftype;

    @Column(name = "Gasoline")
    private Integer gasoline;

    @Column(name = "Diesel")
    private Integer diesel;

    @Column(name = "PHEV")
    private Integer phev;

    @Column(name = "EV")
    private Integer ev;

    @Column(name = "MSERIES")
    private Integer mseries;

    @Column(name = "SERIES")
    private String series;

    @Column(name = "UKL")
    private Integer ukl;

    @Column(name = "KKL")
    private Integer kkl;

    @Column(name = "MKL")
    private Integer mkl;

    @Column(name = "GKL")
    private Integer gkl;

    @Column(name = "LEG")
    private Double leg;

    @Column(name = "TRUNK1")
    private Double trunk1;

    @Column(name = "TRUNK2")
    private Double trunk2;

    @Column(name = "LUXURY")
    private Integer luxury;

    @Column(name = "DESIGN")
    private Integer design;

    @Column(name = "LWH")
    private String lwh;

    @Column(name = "Length")
    private Integer length;

    @Column(name = "Width")
    private Integer width;

    @Column(name = "Height")
    private Integer height;

    @Column(name = "SOUND")
    private Double sound;

    @Column(name = "LEDK")
    private Integer ledk;

    @Column(name = "ENTERP")
    private Integer enterp;

    @Column(name = "LIGHT")
    private Integer light;

    @Column(name = "COMFORTABLE_INTERIOR")
    private Integer comfortableInterior;

    @Column(name = "HUD")
    private Double hud;

    @Column(name = "driving")
    private Double driving;

    @Column(name = "Parking_assistance")
    private Double parkingAssistance;

    @Column(name = "DRIVINGASSISTANCE")
    private Double drivingassistance;

    @Column(name = "popname")
    private String popname;

    @Column(name = "icon")
    private String icon;

    @Column(name = "image")
    private String image;

    @Column(name = "popimage")
    private String popimage;

    @Column(name = "popsub")
    private String popsub;

    @Column(name = "link")
    private String link;

    @Column(name = "radar_performance")
    private Double radarPerformance;

    @Column(name = "radar_price")
    private Double radarPrice;

    @Column(name = "radar_space")
    private Double radarSpace;

    @Column(name = "radar_fuel")
    private Double radarFuel;

    @Column(name = "radar_popularity")
    private Double radarPopularity;

    @Column(name = "radar_fuele")
    private String radarFuele;

    @Column(name = "radar_costkm")
    private String radarCostkm;

    @Column(name = "radar_legtrunk")
    private String radarLegtrunk;

    @Column(name = "radar_won")
    private String radarWon;

    @Column(name = "radar_weight")
    private String radarWeight;

    @Column(name = "radar_power")
    private String radarPower;

    @Column(name = "radar_keywords")
    private String radarKeywords;
}
