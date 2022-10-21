package com.training.classActivities.ReadFromFiles

import scala.io.Source

object ReadFileConents extends App{
        println(Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").mkString)
        Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").getLines().foreach(z=> println("Line content is "+z))
        Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").getLines().take(1).foreach(println)
        Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").getLines().slice(0,2).foreach(println)
        println(Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").toList)
        println(Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\com\\training\\classActivities\\ReadFromFiles\\Demo3.txt").toArray)
        }
