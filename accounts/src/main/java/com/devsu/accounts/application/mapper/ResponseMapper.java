package com.devsu.accounts.application.mapper;

import com.devsu.accounts.application.dto.InfoResponseDTO;
import com.devsu.accounts.application.dto.ResponseDTO;

import java.time.LocalDateTime;

public class ResponseMapper {

    public ResponseDTO responseDTO(String message, String status) {

        InfoResponseDTO infoResponseDTO = new InfoResponseDTO();
        infoResponseDTO.setMessage(message);
        infoResponseDTO.setStatus(status);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(infoResponseDTO);
        responseDTO.setOperationDate(LocalDateTime.now().toString());

        return responseDTO;
    }

    public ResponseDTO responseDTO(Object data) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(data);
        responseDTO.setOperationDate(LocalDateTime.now().toString());

        return responseDTO;
    }

}
