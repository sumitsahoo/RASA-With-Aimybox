package com.sumit.assistant.util

class AppConstants {

    interface AimyboxSettings {
        companion object {
            const val DEFAULT_AIMYBOX_API_KEY = <enter your key here>
        }
    }

    interface SharedPreferencesKey {
        companion object {

            const val PREF_NAME = "assistant_preferences"

            const val KEY_DATA_SOURCE_TYPE = "key_data_source_type"
            const val KEY_DATA_SOURCE_VALUE = "key_data_source"
            const val KEY_ANIMATION = "key_animation"

        }
    }

    interface NLUDataSource {
        companion object {
            const val DEFAULT = 0
            const val CUSTOM_AIMYBOX = 1
            const val CUSTOM_RASA = 2
            const val CUSTOM_DIALOGFLOW = 3
        }
    }

}
