package com.sumit.assistant.util

class AppConstants {

    public interface AimyboxSettings {
        companion object {
            const val DEFAULT_AIMYBOX_API_KEY = "D01BipNn0ESFmVXSKUkpuHmdjdM6wNzV"
        }
    }

    public interface SharedPreferencesSettings {
        companion object {

            const val PREF_NAME = "assistant_preferences"

            const val KEY_DATA_SOURCE = "key_data_source"
            const val KEY_ANIMATION = "key_animation"

        }
    }

}
