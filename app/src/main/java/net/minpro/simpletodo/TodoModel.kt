package net.minpro.simpletodo

import io.realm.RealmObject

open class TodoModel: RealmObject() {

    //タイトル
    var title: String = ""

    //期日(yyyy/MM/dd)
    var deadLine: String = ""

    //タスク内容
    var taskDetail: String = ""

    //完了フラグ
    var isCompleted: Boolean = false


}