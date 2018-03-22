package com.yateam.parnasapp.models

import com.yateam.parnasapp.models.Fields

/**
 * Created by name on 04.03.2018.
 */
open class Profile(var id: String? = null,
                   var created: String? = null,
                   var changed: String? = null,
                   var email: String? = null,
                   var emailVerified: Boolean,
                   var approved: Boolean,
                   var blocked: Boolean,
                   var userSessionId: String,
                   var fields: Fields) {
    /*{
        "id":"91a86472-4273-4830-b8df-d3d89f8bb2f5",
        "created":"2017-08-21T17:56:58+03:00",
        "changed":"2018-03-04T01:33:05+03:00",
        "email":"admin@mail.ru",
        "phone":null,
        "emailVerified":true,
        "phoneVerified":false,
        "approved":true,
        "blocked":false,
        "fields":{
        "name":"Ян",
        "surname":"Трубецкой",
        "patronymic":"Николаевич"
    },
        "roles":[
        ],
        "formattedUserName":"Ян Трубецкой",
        "locale":null,
        "grants":[
        "USER:SHOW",
        "USER:SAVE",
        "PROPERTY:DELETE",
        "TASK:SAVE",
        "TASK:SHOW",
        "USER:DELETE",
        "TASK:RESET",
        "PROPERTY:SAVE",
        "PROPERTY:SHOW",
        "ROLE:SHOW",
        "ROLE:SAVE",
        "TASK:DELETE",
        "ROLE:DELETE"
        ],
        "userSessionId":"e348675f-511e-45af-81b6-8553465418c8"
    }*/
}