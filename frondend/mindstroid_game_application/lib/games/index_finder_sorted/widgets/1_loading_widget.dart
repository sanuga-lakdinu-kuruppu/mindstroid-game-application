import 'package:flutter/material.dart';
import 'package:mindstroid_game_application/games/index_finder_searching/widgets/2_name_widget.dart';
import 'package:percent_indicator/circular_percent_indicator.dart';

import '../../../constants/image_strings.dart';
import '2_name_widget.dart';

class LoadingWidgetFour extends StatefulWidget {
  const LoadingWidgetFour({super.key});

  @override
  State<LoadingWidgetFour> createState() => _LoadingWidgetFourState();
}

class _LoadingWidgetFourState extends State<LoadingWidgetFour> {

  bool showMessage = false;

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(milliseconds: 1000), () {
      setState(() {
        showMessage = true;
      });
    });
  }

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        children: [
          loading(),
        ],
      ),
    );
  }

  Widget loading(){
    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          CircularPercentIndicator(
            animation: true,
            animationDuration: 1000,
            radius: 200,
            lineWidth: 40,
            percent: 1.0,
            progressColor: Colors.white,
            backgroundColor: Colors.white70,
            circularStrokeCap: CircularStrokeCap.round,
            center: Text(
              "100%",
              style: TextStyle(
                color: Colors.black,
                fontSize: 30,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          if (showMessage)
            _buildDialog(),
        ],
      ),
    );
  }

  void _showDialog() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return StatefulBuilder(
          builder: (BuildContext context, StateSetter setState) {
            return Dialog(
              backgroundColor: Colors.white,
              shape: RoundedRectangleBorder(
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
                    NameWidgetFour(),
                  ],
                ),
              ),
            );
          },
        );
      },
    );
  }

  Widget _buildDialog() {
    WidgetsBinding.instance?.addPostFrameCallback((_) => _showDialog());
    return SizedBox.shrink(); // Placeholder widget, will not be visible
  }
}
