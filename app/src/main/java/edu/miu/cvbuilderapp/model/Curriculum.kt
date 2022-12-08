package edu.miu.cvbuilderapp.model

data class Curriculum(val careerNote: String, val coreCompetency: String,
val aboutMe: String,
val educations: List<Education>,
val certifications: List<Certification>,
val workExperiences: List<WorkExperience>,
val contacts: List<Contact>) : java.io.Serializable
