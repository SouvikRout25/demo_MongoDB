package com.example.dem1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND,reason = "customer id is not found")
public class CustomerNotfoundException extends Exception{

}