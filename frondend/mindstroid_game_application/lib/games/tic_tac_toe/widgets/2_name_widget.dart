import 'package:flutter/material.dart';
import 'package:awesome_dialog/awesome_dialog.dart';
import 'package:mindstroid_game_application/games/index_finder_searching/widgets/3_start_menu_widget.dart';
import 'package:mindstroid_game_application/games/index_finder_sorted/widgets/3_start_menu_widget.dart';

import '../../../constants/image_strings.dart';
import '../../../constants/text_strings.dart';
import '3_start_menu_widget.dart';

class NameWidgetThree extends StatefulWidget {
  const NameWidgetThree({super.key});

  @override
  State<NameWidgetThree> createState() => _NameWidgetThreeState();
}

class _NameWidgetThreeState extends State<NameWidgetThree> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController _nameController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Column(
        children: [
          text(),
          nameField(),
          loadingButton(),
        ],
      ),
    );
  }

  Widget nameField() {
    return Padding(
      padding: const EdgeInsets.all(30),
      child: TextField(
        controller: _nameController,
        textAlign: TextAlign.center,
        style: TextStyle(
          color: Colors.white,
          fontFamily: inspirationRegular,
          fontSize: 32,
          fontWeight: FontWeight.bold,
        ),
        decoration: InputDecoration(
          fillColor: Colors.black,
        ),
      ),
    );
  }

  Widget loadingButton() {
    return Padding(
      padding: const EdgeInsets.all(30),
      child: ElevatedButton(
        onPressed: () {
          if (_nameController.text.isEmpty) {
            AwesomeDialog(
              width: 400,
              context: context,
              dialogType: DialogType.warning,
              animType: AnimType.bottomSlide,
              title: 'Warning',
              desc: 'Please enter the Play Name',
              btnCancelOnPress: () {},
            ).show();
          } else {
            showDialog(
              context: context,
              builder: (context) {
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
                        image: backgroundImageGameThree,
                        fit: BoxFit.cover,
                      ),
                    ),
                    padding: const EdgeInsets.all(50.0),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        StartMenuWidgetThree(player: _nameController.text),
                      ],
                    ),
                  ),
                );
              },
            );
          }
        },
        child: const Text(
          "Start",
          style: TextStyle(
            fontFamily: inspirationRegular,
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.black,
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
    );
  }

  Widget text() {
    return Padding(
      padding: const EdgeInsets.all(30),
      child: Text(
        "Enter the Play Name",
        style: TextStyle(
          color: Colors.white,
          fontSize: 32,
          fontFamily: inspirationRegular,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }

  @override
  void dispose() {
    _nameController.dispose();
    super.dispose();
  }
}
