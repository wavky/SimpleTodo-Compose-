package com.wavky.simpletodo.domain

import com.wavky.simpletodo.domain.infra.db.dbModule
import com.wavky.simpletodo.domain.repository.repositoryModule

val domainModules = dbModule + repositoryModule
