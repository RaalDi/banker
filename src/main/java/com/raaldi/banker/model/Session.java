package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.raaldi.banker.util.EnumSessionState;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT c FROM Session c"), })
@Data
@EqualsAndHashCode(callSuper = false)
public class Session extends Model {

    private static final long serialVersionUID = 5729958504572843209L;

    @Id
    @SequenceGenerator(name = "session-seq-gen", sequenceName = "session_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-seq-gen")
    private Long id;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private EnumSessionState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started", insertable = false, updatable = true)
    private Date started;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ended", insertable = false, updatable = true)
    private Date ended;

    public void setState(final EnumSessionState state) {

        switch (state) {
            case STARTING:
                this.setCreated(new Date());
                break;
            case STARTED:
                this.setStarted(new Date());
                break;
            case ENDING:
                this.setUpdated(new Date());
                break;
            case ENDED:
                this.setEnded(new Date());
                break;
        }

        this.state = state;
    }

    public void setStarted(final Date started) {
        this.started = started != null ? new Date(started.getTime()) : null;
    }

    public Date getStarted() {
        return started != null ? new Date(started.getTime()) : null;
    }

    public void setEnded(final Date ended) {
        this.ended = ended != null ? new Date(ended.getTime()) : null;
    }

    public Date getEnded() {
        return ended != null ? new Date(ended.getTime()) : null;
    }

    @Override
    @PrePersist
    public void onPersist() {
        this.setState(EnumSessionState.STARTING);
    }
}
