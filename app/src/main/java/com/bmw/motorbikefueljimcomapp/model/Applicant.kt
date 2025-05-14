package com.bmw.motorbikefueljimcomapp.model





class Applicant{
    var name: String = ""
    var IdNumber: String = ""
    var loanAmount: String = ""
    var loanPurpose: String = ""
    var applicationDate: String = ""


    var id:String=""

    constructor(name:String,IdNumber:String,loanAmount:String,loanPurpose:String,applicationDate:String,id:String){
        this.name=name
        this.IdNumber=IdNumber
        this.loanAmount=loanAmount
        this.loanPurpose=loanPurpose
        this.applicationDate=applicationDate
        this.id=id

    }
    constructor()
}