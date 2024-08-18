package com.ttit.myapp.api;

import java.io.IOException;

public interface TtitCallback {
    void onSuccess(String result);
    void onFailure(IOException e);
}
