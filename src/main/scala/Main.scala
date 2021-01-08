import java.sql._

object Main extends App {
  var con: Connection = _

  val query =
  """
  SELECT clicks, MAX(numClicks) AS most_clicks
  FROM wikiclicks
  GROUP BY clicks
  ORDER BY most_clicks
  """



  try {
    val connectionString = "jdbc:hive2://localhost:10000/default"
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    con = DriverManager.getConnection(connectionString, "", "")

    val statement = con.createStatement()
    var maxSet = statement.executeQuery(
      "SELECT MAX(clicks) AS most_clicks " +
      "FROM wikiclicks " +
      "ORDER BY most_clicks")
    maxSet.next()
    val mostClicks = maxSet.getInt("most_clicks")

    println("Most clicks = " + mostClicks)

    var resultSet = statement.executeQuery(s"""
      SELECT prev, curr, clicks 
      FROM wikiclicks 
      WHERE clicks = $mostClicks
      ORDER BY clicks DESC LIMIT 1""")


    // var resultSet = statement.executeQuery(
    //   "SELECT prev, curr, MAX(clicks) as most_clicks " +
    //   "FROM wikiclicks " +
    //   "GROUP BY prev, curr " +
    //   "ORDER BY most_clicks DESC LIMIT 1")

    var counter = 0;

    while (resultSet.next && counter < 1000) {
      val prev = resultSet.getString("prev")
      val curr = resultSet.getString("curr")
      // val clicktype = resultSet.getString("type")
      val clicks = resultSet.getInt("clicks")
      // println(prev + " " + curr + " " + clicktype + " " + clicks)
      println(prev + " " + curr + " " + clicks)
      counter = counter + 1;
    }    
  } catch {
    case e: Exception => e.printStackTrace()
  } 
  
  con.close();
}