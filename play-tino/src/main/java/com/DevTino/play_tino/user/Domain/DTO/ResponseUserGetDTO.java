package com.DevTino.play_tino.user.Domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResponseUserGetDTO {
    UUID userId;
    String userName;
}
