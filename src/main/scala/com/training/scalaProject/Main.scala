package com.training.scalaProject

import java.sql._
import scala.io.Source

object Main extends App{
        val loaddata = new LoadData
        val bofa = loaddata.bofa
        var nico = bofa.customers(0)

        println(bofa.loans)
        println(nico.loans(0).displayLoanStatus())


}
