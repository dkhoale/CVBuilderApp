package edu.miu.cvbuilderapp.model

import java.time.LocalDate

data class WorkExperience(val position: String, val company: CompanyMapping,
val from: LocalDate, val to: LocalDate, val location: String, val description: String) : java.io.Serializable{

}
