package com.client.rasplus.message.api.service;

import com.client.rasplus.message.api.dto.UserRecoveryCodeDto;

public interface RecoveryCodeService {

    void sendEmail(UserRecoveryCodeDto dto);

}
