import * as React from 'react';

import { StyleSheet, Text, View } from 'react-native';
import { safeArea } from 'react-native-safe-area';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    console.log('-----', safeArea.topSafeArea, safeArea.bottomSafeArea);
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
