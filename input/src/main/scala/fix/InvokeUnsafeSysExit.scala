/*
rule = UnsafeSysExit
 */
package fix


object InvokeUnsafeSysExit extends App {
  System.exit(0)// assert: UnsafeSysExit
}
