import 'package:flutter/material.dart';
import 'package:mindstroid_game_application/constants/image_strings.dart';
import 'package:mindstroid_game_application/games/index_finder_sorted/widget/1_loading_widget.dart';

import 'constants/text_strings.dart';


class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  final ScrollController _scrollController = ScrollController();
  int currentIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.white70, // Set the background color here
        child: Column(
          children: [
            Row(
              children: [
                IconButton(
                  icon: Icon(Icons.arrow_back),
                  onPressed: () {
                    _scrollToPrevious();
                  },
                ),
                Expanded(
                  child: SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    controller: _scrollController,
                    child: Row(
                      children: [
                        gameOne(),
                        gameTwo(),
                        gameFive(),
                        gameThree(),
                        gameFour(),
                      ],
                    ),
                  ),
                ),
                IconButton(
                  icon: Icon(Icons.arrow_forward),
                  onPressed: () {
                    _scrollToNext();
                  },
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget gameOne() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 70),
      child: Container(
        height: MediaQuery.of(context).size.height * 0.7,
        width: MediaQuery.of(context).size.width * 0.3,
        decoration: BoxDecoration(
          color: Colors.green,
          borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
        ),
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Text(
              "Home 1",
              style: TextStyle(
                color: Colors.black,
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget gameTwo() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 70),
      child: Container(
        height: MediaQuery.of(context).size.height * 0.7,
        width: MediaQuery.of(context).size.width * 0.3,
        decoration: BoxDecoration(
          color: Colors.green,
          borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
        ),
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Text(
              "Home 2",
              style: TextStyle(
                color: Colors.black,
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget gameThree() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 70),
      child: Container(
        height: MediaQuery.of(context).size.height * 0.7,
        width: MediaQuery.of(context).size.width * 0.3,
        decoration: BoxDecoration(
          color: Colors.green,
          borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
        ),
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            Text(
              "Home 3",
              style: TextStyle(
                color: Colors.black,
                fontSize: 32,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget gameFour() {
    return Container(
      margin: EdgeInsets.all(20),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 70),
        child: Container(
          height: MediaQuery.of(context).size.height * 0.7,
          width: MediaQuery.of(context).size.width * 0.3,
          decoration: BoxDecoration(
            image: DecorationImage(
              image: backgroundImageGameFive,
              fit: BoxFit.cover,
            ),
            color: Colors.green,
            borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
          ),
          alignment: Alignment.center,
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                ElevatedButton(
                  onPressed: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return StatefulBuilder(
                          builder: (context, setStateForDialog) {
                            return Dialog(
                              backgroundColor: Colors.white,
                              shape: const RoundedRectangleBorder(
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                              ),
                              child: Container(
                                width: 600,
                                height: 640,
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(10),
                                  image: DecorationImage(
                                    image: backgroundImageGameFour,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                                padding: const EdgeInsets.all(50.0),
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    // LoadingWidget4(),
                                  ],
                                ),
                              ),
                            );
                          },
                        );
                      },
                    );
                  },
                  child: const Text(
                    "Play",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                      fontFamily: inspirationRegular,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.white,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10),
                    ),
                    minimumSize: Size(150, 50),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget gameFive() {
    return Container(
      margin: EdgeInsets.all(20),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 70),
        child: Container(
          height: MediaQuery.of(context).size.height * 0.7,
          width: MediaQuery.of(context).size.width * 0.3,
          decoration: BoxDecoration(
            image: DecorationImage(
              image: backgroundImageGameFive,
              fit: BoxFit.cover,
            ),
            color: Colors.green,
            borderRadius: BorderRadius.circular(10), // Adjust the radius as needed
          ),
          alignment: Alignment.center,
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                ElevatedButton(
                  onPressed: () {
                    showDialog(
                      context: context,
                      builder: (context) {
                        return StatefulBuilder(
                          builder: (context, setStateForDialog) {
                            return Dialog(
                              backgroundColor: Colors.white,
                              shape: const RoundedRectangleBorder(
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                              ),
                              child: Container(
                                width: 600,
                                height: 640,
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(10),
                                  image: DecorationImage(
                                    image: backgroundImageGameFive,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                                padding: const EdgeInsets.all(50.0),
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    // WidgetGameFive(),
                                    // StartMenu(),
                                  ],
                                ),
                              ),
                            );
                          },
                        );
                      },
                    );
                  },
                  child: const Text(
                    "Play",
                    style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.black,
                      fontFamily: inspirationRegular,
                    ),
                  ),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.white,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10),
                    ),
                    minimumSize: Size(150, 50),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void _scrollToPrevious() {
    if (currentIndex > 0) {
      currentIndex--;
      _scrollController.animateTo(
        currentIndex * MediaQuery.of(context).size.width * 0.3 + currentIndex * 20, // Adjust the container width and padding
        duration: Duration(milliseconds: 500),
        curve: Curves.easeInOut,
      );
    }
  }

  void _scrollToNext() {
    if (currentIndex < 4) {
      currentIndex++;
      _scrollController.animateTo(
        currentIndex * MediaQuery.of(context).size.width * 0.3 + currentIndex * 20, // Adjust the container width and padding
        duration: Duration(milliseconds: 500),
        curve: Curves.easeInOut,
      );
    }
  }
}

void main() {
  runApp(MaterialApp(
    home: Home(),
  ));
}
