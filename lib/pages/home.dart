import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Home extends StatefulWidget {
  const Home({Key key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  static const _methodChannel = MethodChannel("com.example.ch12_platform_channel/deviceinfo");

  //get device info
  String _deviceInfo = "";

  Future<void> _getDeviceInfo() async{
    String deviceInfo;
    try{
      deviceInfo = await _methodChannel.invokeMethod("getDeviceInfo");
    }on PlatformException catch(e){
      deviceInfo = "Failed to get device info '${e.message}'.";
    }
    setState(() {
      _deviceInfo = deviceInfo;
    });
  }


  @override
  void initState() {
    super.initState();
    _getDeviceInfo();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Platform Channel'),
      ),
      body: SafeArea(
        child: ListTile(
          title: Text(
            'Device Info:',
            style: TextStyle(
              fontSize: 24.0,
              fontWeight: FontWeight.bold,
            ),
          ),
          subtitle: Text(
            _deviceInfo,
            style: TextStyle(
              fontSize: 18.0,
              fontWeight: FontWeight.bold,
            ),
          ),
          contentPadding: EdgeInsets.all(16.0),
        ),
      ),
    );
  }
}
