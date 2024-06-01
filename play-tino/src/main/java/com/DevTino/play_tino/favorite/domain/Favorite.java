package com.DevTino.play_tino.favorite.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Favorite {
    @Id
    UUID favoriteId;

    String favoriteImage;
    String favoriteTitle;
}
