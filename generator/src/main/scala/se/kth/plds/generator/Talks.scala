package se.kth.plds.generator

import java.util.Date
import scala.concurrent.duration._

case class Talk(speaker: String, affiliation: String, title: String, `abstract`: String) {
  import Utils.AddableDate;

  def asSlot(startTime: Date, length: Duration): TalkSlot = {
    val endTime: Date = startTime.addMinutes(length.toMinutes.toInt);
    TalkSlot(this, startTime, endTime)
  }
}
case class TalkSlot(talk: Talk, startTime: Date, endTime: Date) {
  def startTimeOnly: String = Utils.getTimeAsString(startTime);
  def endTimeOnly: String = Utils.getTimeAsString(endTime);
}

object Talks {
  import Utils.DateString;

  lazy val list: List[TalkSlot] = List(
    // Thursday
    coffee.asSlot("05 12:30".toConfDate, 30.minutes),
    philipp.asSlot("05 13:00".toConfDate, 30.minutes),
    peter.asSlot("05 13:30".toConfDate, 30.minutes),
    paris.asSlot("05 14:00".toConfDate, 30.minutes),
    klas.asSlot("05 14:30".toConfDate, 30.minutes),
    coffee.asSlot("05 15:00".toConfDate, 30.minutes),
    holger.asSlot("05 15:30".toConfDate, 30.minutes), // Skype maybe more time
    cosmin.asSlot("05 16:00".toConfDate, 2.hours),
    // Friday
    breakfast.asSlot("06 08:30".toConfDate, 30.minutes),
    jim.asSlot("06 09:00".toConfDate, 30.minutes),
    guido.asSlot("06 09:30".toConfDate, 30.minutes),
    saranya.asSlot("06 10:00".toConfDate, 30.minutes),
    undecided.asSlot("06 10:30".toConfDate, 30.minutes), // Heather?
    kostis.asSlot("06 11:00".toConfDate, 60.minutes)
  );

  val undecided = Talk("Reserved Slot", "", "TBD", "");

  val coffee = Talk("Coffee, tea, and cookies will be served in the workshop room.", "", "Coffee Break", "");

  val breakfast = Talk("Coffee, tea, and sandwiches will be served in the workshop room.", "", "Breakfast", "");

  val cosmin = Talk(
    "Cosmin Arad",
    "Google Cloud, Big Data Analytics",
    "Fundamentals of Stream Data Processing with Apache Beam and Google Cloud Dataflow",
    """<p>This talk presents a unified model for streaming and batch data processing. We start from the history of large-scale data processing systems at Google and their evolution towards higher levels of abstraction culminating in the Beam programming model. We delve into the fundamentals of out-of-order unbounded data stream processing and we show how Beam's powerful abstractions for reasoning about time greatly simplify this complex task. Beam provides a model that allows developers to focus on the four important questions that must be answered by any stream processing pipeline:
<ul>
<li><em>What</em> results are being calculated,</li>
<li><em>Where</em> in event time they are calculated,</li>
<li><em>When</em> in processing time they are materialized, and</li>
<li><em>How</em> refinements of results relate.</li>
</ul>
</p>
<p>We discuss how these key questions and abstractions enable important properties of correctness, expressive power, composability, flexibility, and modularity. Furthermore, by cleanly separating these questions from runtime characteristics, Beam programs become portable across multiple runtime environments, both proprietary, such as Google Cloud Dataflow, and open-source, such as Flink, Spark, Samza, etc. We close with a presentation of the execution model and show how the Google Cloud Dataflow service optimizes and orchestrates the distributed execution and autoscaling of a Beam pipeline.</p>"""
  );

  val holger = Talk(
    "Holger Pirk",
    "Imperial College London",
    "Dark silicon — a currency we do not control",
    """The breakdown of dennard scaling changed the game of processor design: no longer can the entire die be filled with “always-on” components — some regions must be powered up and down at runtime to prevent the chip from overheating. Such “dim” or “dark” silicon is the new currency of chip design, raising the question: what functionality should be implemented in dark silicon? Viable candidates are any non-essential units that support important applications. Naturally, database researchers were quick to claim this resource, arguing that it should be used to implement instructions and primitives supporting database workloads.<br />
In this talk, we argue that, due to economic constraints, such a design is unlikely to be implemented in mainstream server chips. Instead, chip designers will spend silicon on high-volume market segments such as AI, Security or Graphics/AR which require a different set of primitives. Consequently, database researchers need to find uses for the actual functionality of chips rather than wishing for features that are economically infeasible. Let us develop innovative ways to exploit the “hardware we have, not the hardware we wish to have at a later time”. In the talk, we discuss examples of creative use of hardware for data management purposes such as TLBs for MVCC, Transactional Memory for statistics collection and hardware graphics shaders for data analytics. We also highlight some processor functionality that still calls for creative use such as many floating point instructions, integrated sound processors and some of the model-specific registers."""
  );

  val peter = Talk(
    "Peter Van Roy",
    "Université catholique de Louvain",
    "Why time is evil and what to do about it: designing distributed systems as pure functional programs plus interaction points",
    """There exists a useful purely functional subset of distributed programming. Purely functional distributed computations do not interact with the real world (because all inputs must be known in advance), but they support message asynchrony and reordering, and they can be used to build networks of communicating agents.  General distributed programming consists of purely functional distributed programming plus interaction points for real-world interactions.  Experience shows that realistic distributed systems are mostly functional, i.e., they have very few interaction points.  We give a precise formal definition of interaction points and we present a design language, called PROP (Piecewise Relative Observable Purity) to specify distributed systems explicitly as a purely functional core plus interaction points.  We aim to turn this into a practical tool that can leverage the powerful techniques available to functional programming for distributed systems design."""
  );

  val klas = Talk(
    "Klas Segeljakt",
    "KTH Royal Institute of Technology",
    "Arc: An MLIR dialect for Data Analytics",
    """<p>Modern day end-to-end data analytics pipelines are centered around transforming various kinds of collection-based data types, including tensors, tables, graphs, and streams. Current data analyics frameworks are typically designed around one or two of these data types. Examples of such frameworks include Apache Calcite which leverages relational streams, Halide which supports streaming stencil computations, and Lara offers unified support for operations on bags with matrices.</p>
<p>In the CDA project, the goal is to leverage the ability to express and optimise operations over more than two types of collections. To this end, we propose Arc, an intermediate representation for data analytics. Arc is implemented as a dialect in MLIR (Multi-Level IR) which is a highly modular and hackable compiler framework built on top of LLVM. This talk will introduce the design ideas behind the latest installment of Arc, and will give an overview of MLIR and its role in achieving our goal.</p>"""
  );

  val paris = Talk(
    "Paris Carbone",
    "RISE Research Institutes of Sweden",
    "TBD",
    ""
  );

  val philipp = Talk(
    "Philipp Haller",
    "KTH Royal Institute of Technology",
    "Selected challenges in concurrent and distributed programming",
    """We present three challenges in concurrent and distributed programming, as well as recent results addressing them. The first challenge consists of ensuring fault-tolerance properties in typed programming languages. The main question is how to enforce fault-tolerance properties for well-typed programs, as opposed to specific algorithms or systems. Towards addressing this question, we present the first correctness results for a typed calculus with first-class lineages. The second challenge consists of using data with different consistency properties safely within the same distributed application. To address this challenge, we propose a novel type system which provides a noninterference guarantee: mutations of potentially-inconsistent data cannot be observed via access to consistent data types. As a third challenge we propose the design of a concurrent domain-specific language for parallelizing static analysis problems."""
  );

  val kostis = Talk(
    "Kostis Sagonas",
    "Department of Information Technology, Uppsala University",
    "Experiences from Testing and Verifying “Real-World” Concurrent and Distributed Systems",
    """<p>This talk will report on some experiences in applying two different stateless model checking (SMC) tools in two different case studies. The first of them applied Nidhugg, an SMC tool for C/Pthread programs, to the code of Tree RCU, the Hierarchical Read-Copy-Update synchronization mechanism for mutual exclusion used in the Linux kernel, a low-level and quite complex concurrent program. The second case study applied Concuerror, an SMC tool for Erlang programs, to test and verify, during their design phase by engineers at VMWare, chain repair methods for CORFU, a distributed shared log which aims to be scalable and reliable in the presence of failures and asynchrony.</p>
    
<p>Besides the results from these two case studies, we will try to present some experiences and lessons learned about engaging in testing projects of that size and complexity.</p>"""
  );

  val guido = Talk(
    "Guido Salvaneschi",
    "Technical University of Darmstadt",
    "Why languages for distributed systems are inevitable",
    """Over the last few years, ubiquitous connectivity has led to data being constantly generated at an unprecedented rate. As a result, large amounts of data are constantly being processed in a heterogeneous infrastructure which stems from the convergence of edge (IoT, mobile) and cloud computing. This poses fundamental challenges in software design, especially with respect to fault tolerance, data consistency, and privacy.<br />
In this presentation, we discuss recent research results we achieved in this context at various levels. We describe an innovative programming framework that improves and simplifies the design of data-intensive applications. We also present the use of our programming framework on real-world case studies, emphasizing how to achieve fault tolerance and data consistency. Finally, we propose how to account for privacy in the software engineering process for data-intensive distributed applications."""
  );

  val saranya = Talk("Saranya Natarajan", "KTH Royal Institute of Technology", "TBD", "");

  val jim = Talk(
    "Jim Dowling",
    "Logical Clocks/KTH Royal Institute of Technology",
    "Towards Distribution Transparency for Supervised ML With Oblivious Training Functions",
    """Building and productionizing Machine Learning (ML) models is a process of interdependent steps of iterative code updates, including exploratory model design, hyperparameter tuning, ablation experiments, and model training. Industrial-strength ML involves doing this at scale, using many compute resources, and this requires rewriting the training code to account for distribution. The result is that moving from a single host program to a cluster hinders iterative development of the software, as iterative development would require multiple versions of the software to be maintained and kept consistent. In this talk, we introduce the distribution oblivious training function as an abstraction for ML development in Python, whereby developers can reuse the same training function when running a notebook on a laptop or performing scale-out hyperparameter search and distributed training on clusters. Programs written in our framework look like industry-standard ML programs as we factor out dependencies using best-practice programming idioms (such as functions to generate models and data batches). We believe that our approach takes a step towards unifying single-host and distributed ML development."""
  )
}
