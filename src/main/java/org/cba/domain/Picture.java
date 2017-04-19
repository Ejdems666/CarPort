package org.cba.domain;

import org.cba.domain.finder.PictureFinder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by AR on 4/19/2017.
 */
@Entity
public class Picture {

  public static final PictureFinder find = new PictureFinder();

    @Id
    private Integer id;

    @NotNull
    private String url;

    @ManyToOne
    private Carport carport;
    @NotNull
    private boolean thumbnail;

    public boolean isThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }
}
