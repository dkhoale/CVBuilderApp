package edu.miu.cvbuilderapp.model

import edu.miu.cvbuilderapp.R

enum class CompanyMapping(val icon: Int) {
    VISA(R.drawable.visa_icon),
    IBM(R.drawable.ibm_icon),
    WALMART(R.drawable.walmart_logo),
    GOOGLE(R.drawable.google_icon);

    companion object {
        fun findIconByName(companyName: String): Int {
            return CompanyMapping.values().findLast {
                it.name.equals(companyName, true)
            }?.icon ?: R.drawable.workplace_icon
        }
    }
}